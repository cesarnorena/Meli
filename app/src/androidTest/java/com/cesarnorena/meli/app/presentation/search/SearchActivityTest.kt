package com.cesarnorena.meli.app.presentation.search

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.cesarnorena.meli.R
import com.cesarnorena.meli.app.injection.BaseUrlModule
import com.cesarnorena.meli.app.library.extensions.registerIdle
import com.cesarnorena.meli.app.library.extensions.unregisterIdle
import com.hitanshudhawan.jsondsl.json
import com.hitanshudhawan.jsondsl.jsonArray
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Rule
import org.junit.Test

@UninstallModules(BaseUrlModule::class)
@HiltAndroidTest
class SearchActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val mockServer = MockWebServer()

    @BindValue
    @JvmField
    val baseUrl: HttpUrl = mockServer.url("/")

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun showErrorMessageWithServerError() {
        mockServer.enqueue(MockResponse().setResponseCode(500))

        val scenario = launchActivity()

        onView(withId(R.id.search_src_text))
            .perform(typeTextIntoFocusedView("Motorola"))

        onView(withId(R.id.search_src_text))
            .perform(pressImeActionButton())

        onView(withText(R.string.activity_search_error))
            .check(matches(isDisplayed()))

        scenario.closeActivity()
    }

    @Test
    fun showSearchListWithValidQuery() {
        val product = json {
            "id" to "1234"
            "title" to "Moto G6"
            "price" to 10000
            "thumbnail" to "https://http2.mlstatic.com/D_NQ_NP_669939-MLA44492818154_012021-W.webp"
            "currency_id" to "BRL"
        }
        val body = json { "results" to jsonArray(product) }.toString()
        mockServer.enqueue(MockResponse().setResponseCode(200).setBody(body))

        val scenario = launchActivity()

        onView(withId(R.id.search_src_text))
            .perform(typeTextIntoFocusedView("Motorola"))

        onView(withId(R.id.search_src_text))
            .perform(pressImeActionButton())

        onView(
            Matchers.allOf(
                withId(R.id.product_title),
                ViewMatchers.isDescendantOfA(withId(R.id.product_container))
            )
        ).check(matches(withText(product["title"] as String)))

        scenario.closeActivity()
    }

    private fun launchActivity(): ActivityScenario<SearchActivity> {
        val scenario = ActivityScenario.launch(SearchActivity::class.java)
        scenario.onActivity { it.registerIdle() }
        return scenario
    }

    private fun ActivityScenario<SearchActivity>.closeActivity() {
        onActivity { it.unregisterIdle() }
        close()
    }
}
