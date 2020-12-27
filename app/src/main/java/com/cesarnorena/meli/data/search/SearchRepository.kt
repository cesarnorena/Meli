package com.cesarnorena.meli.data.search

import com.cesarnorena.meli.data.search.model.SearchResult

interface SearchRepository {
    suspend fun get(query: String): SearchResult
}

class DefaultSearchRepository(
    private val connector: SearchConnector
) : SearchRepository {

    override suspend fun get(query: String): SearchResult {
        val response = connector.searchProducts("MLA", query)
        if (response.code() != 200) throw Exception()
        return response.body() ?: throw Exception()
    }
}
