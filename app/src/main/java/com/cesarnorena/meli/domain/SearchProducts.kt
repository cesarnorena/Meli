package com.cesarnorena.meli.domain

import com.cesarnorena.meli.data.search.DefaultSearchRepository
import com.cesarnorena.meli.data.search.SearchRepository
import com.cesarnorena.meli.data.site.DefaultSiteRepository
import com.cesarnorena.meli.data.site.SiteRepository
import com.cesarnorena.meli.library.retrofit.ConnectorFactory

class SearchProducts(
    private val siteRepository: SiteRepository = DefaultSiteRepository(),
    private val searchRepository: SearchRepository = DefaultSearchRepository(ConnectorFactory.create())
) {

    suspend operator fun invoke(query: String, offset: Int = 0): List<String> {
        if (query.isEmpty()) return emptyList()

        val siteId = siteRepository.get()
        return searchRepository.get(siteId, query, offset).results.map { it.title }
    }
}
