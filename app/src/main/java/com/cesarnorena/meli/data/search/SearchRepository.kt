package com.cesarnorena.meli.data.search

import com.cesarnorena.meli.data.search.model.SearchResult

interface SearchRepository {
    suspend fun get(siteId: String, query: String, offset: Int): SearchResult
}

class DefaultSearchRepository(
    private val connector: SearchConnector
) : SearchRepository {

    override suspend fun get(siteId: String, query: String, offset: Int): SearchResult {
        val response = connector.searchProducts(siteId, query, offset)
        if (response.code() != 200) throw Exception()
        return response.body() ?: throw Exception()
    }
}
