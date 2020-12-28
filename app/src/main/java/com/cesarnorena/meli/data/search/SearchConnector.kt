package com.cesarnorena.meli.data.search

import com.cesarnorena.meli.data.search.model.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchConnector {

    @GET("/sites/{site_id}/search")
    suspend fun searchProducts(
        @Path("site_id") siteId: String,
        @Query("q") query: String,
        @Query("offset") offset: Int
    ): Response<SearchResult>
}
