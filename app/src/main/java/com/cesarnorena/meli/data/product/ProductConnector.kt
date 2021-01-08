package com.cesarnorena.meli.data.product

import com.cesarnorena.meli.data.search.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductConnector {

    @GET("/items/{id}")
    suspend fun productDetails(
        @Path("id") id: String
    ): Response<ProductResponse>
}
