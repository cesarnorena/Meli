package com.cesarnorena.meli.app.presentation.detail.stateful

import com.cesarnorena.meli.app.presentation.State
import com.cesarnorena.meli.data.product.model.ProductResponse

sealed class DetailState : State {
    object LoadingState : DetailState()
    data class ErrorSate(val error: Throwable) : DetailState()
    data class ProductState(val product: ProductResponse) : DetailState()
}
