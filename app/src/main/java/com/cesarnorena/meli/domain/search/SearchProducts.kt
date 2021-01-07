package com.cesarnorena.meli.domain.search

import com.cesarnorena.meli.data.search.SearchRepository
import com.cesarnorena.meli.data.search.model.SearchItem
import com.cesarnorena.meli.data.site.SiteRepository

class SearchProducts(
    private val siteRepository: SiteRepository,
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: String, offset: Int = 0): List<SearchItem> {
        if (query.isEmpty()) return emptyList()
        val siteId = siteRepository.get()
        return searchRepository.get(siteId, query, offset).results
    }
}
