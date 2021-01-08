package com.cesarnorena.meli.data.search

import com.cesarnorena.meli.data.search.model.SearchResponse

interface SearchRepository {
    suspend fun search(siteId: String, query: String, offset: Int): SearchResponse
}

class DefaultSearchRepository(
    private val connector: SearchConnector
) : SearchRepository {

    override suspend fun search(siteId: String, query: String, offset: Int): SearchResponse {
        val response = connector.search(siteId, query, offset)
        if (response.code() != 200) throw Exception(response.message())
        return response.body() ?: throw Exception()
    }
}
