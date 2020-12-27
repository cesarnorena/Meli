package com.cesarnorena.meli.data.search

import com.cesarnorena.meli.library.retrofit.ConnectorFactory
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DefaultSearchRepositoryTest {

    private val repository = DefaultSearchRepository(ConnectorFactory.get())

    @Test
    fun successResponse() = runBlocking {
        val response = repository.get("motorola g6")
        assert(response.results.isNotEmpty())
    }

    @Test
    fun emptyResponse() = runBlocking {
        val response = repository.get("")
        assert(response.results.isEmpty())
    }
}
