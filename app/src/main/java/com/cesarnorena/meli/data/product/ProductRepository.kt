package com.cesarnorena.meli.data.product

import com.cesarnorena.meli.data.search.model.ProductResponse

interface ProductRepository {
    suspend fun get(id: String): ProductResponse
}

class DefaultProductRepository(
    private val connector: ProductConnector
) : ProductRepository {

    override suspend fun get(id: String): ProductResponse {
        val response = connector.productDetails(id)
        if (response.code() != 200) throw Exception(response.message())
        return response.body() ?: throw Exception()
    }
}
