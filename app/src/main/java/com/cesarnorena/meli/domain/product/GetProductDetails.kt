package com.cesarnorena.meli.domain.product

import com.cesarnorena.meli.data.product.ProductRepository
import com.cesarnorena.meli.data.search.model.ProductResponse
import javax.inject.Inject
import kotlinx.coroutines.withTimeout

class GetProductDetails @Inject constructor(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(id: String): ProductResponse {
        if (id.isEmpty()) throw IllegalArgumentException()
        return withTimeout(6000) {
            productRepository.get(id)
        }
    }
}
