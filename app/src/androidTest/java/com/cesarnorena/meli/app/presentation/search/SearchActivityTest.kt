package com.cesarnorena.meli.app.presentation.search

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.cesarnorena.meli.R
import org.junit.Test

class SearchActivityTest {

    @Test
    fun performSearch() {
        val scenario = launchActivity()

        onView(withId(R.id.search_input))
            .perform(typeText("Motorola"))

        onView(withId(R.id.search_input))
            .perform(pressImeActionButton())

        scenario.close()
    }

    private fun launchActivity() = ActivityScenario.launch(SearchActivity::class.java)
}
