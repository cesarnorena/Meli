package com.cesarnorena.meli.data.search

import com.cesarnorena.meli.library.retrofit.RetrofitFactory
import com.hitanshudhawan.jsondsl.json
import com.hitanshudhawan.jsondsl.jsonArray
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.After
import org.junit.Assert
import org.junit.Test

class SearchRepositoryTest {

    private val mockServer = MockWebServer()

    private val repository: SearchRepository by lazy {
        val baseUrl = mockServer.url("/search/")
        val connector = RetrofitFactory.create<SearchConnector>(baseUrl)
        DefaultSearchRepository(connector)
    }

    private val siteId = "MLB"
    private val offset = 0

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun successResponseWithValidQuery() = runBlocking {
        val product = json {
            "id" to "1234"
            "title" to "Motorola"
            "price" to 10000
            "thumbnail" to "https://http2.mlstatic.com/D_NQ_NP_669939-MLA44492818154_012021-W.webp"
        }
        val body = json { "results" to jsonArray(product) }
        mockResponse(body)

        val response = repository.search(siteId, "motorola g6", offset)
        assert(response.results.isNotEmpty())
    }

    @Test
    fun emptyResponseWithEmptyQuery() = runBlocking {
        mockResponse(json { "results" to jsonArray() })

        val response = repository.search(siteId, "", offset)
        assert(response.results.isEmpty())
    }

    @Test(expected = Exception::class)
    fun errorResponseWithServerError() = runBlocking {
        mockResponse(code = 500)

        repository.search(siteId, "", offset)
        Assert.fail("the repository should have thrown an exception")
    }

    private fun mockResponse(body: JSONObject = json {}, code: Int = 200) {
        val response = MockResponse().setResponseCode(code).setBody(body.toString())
        mockServer.enqueue(response)
    }
}
