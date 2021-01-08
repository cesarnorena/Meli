package com.cesarnorena.meli.app.presentation.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cesarnorena.meli.app.presentation.StatefulViewModel
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailEvent
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailEvent.ProductEvent
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState.ErrorSate
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState.LoadingState
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState.ProductState
import com.cesarnorena.meli.data.search.model.ProductResponse
import com.cesarnorena.meli.domain.product.GetProductDetails
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel @ViewModelInject constructor(
    @Assisted private val savedState: SavedStateHandle,
    private val getProductDetails: GetProductDetails
) : StatefulViewModel<DetailState, DetailEvent>() {

    override val state = MutableLiveData<DetailState>()

    private var product: ProductResponse?
        get() = savedState.getLiveData<ProductResponse>("product").value
        set(value) {
            savedState["product"] = value
        }

    init {
        product?.let {
            state.value = ProductState(it)
        }
    }

    override fun event(event: DetailEvent) {
        when (event) {
            is ProductEvent -> onProductEvent(event.id)
        }
    }

    private fun onProductEvent(id: String) = viewModelScope.launch(IO) {
        state.postValue(LoadingState)

        try {
            val newProduct = getProductDetails(id)
            withContext(Main) { product = newProduct }
            state.postValue(ProductState(newProduct))
        } catch (e: Exception) {
            state.postValue(ErrorSate(e))
        }
    }
}
