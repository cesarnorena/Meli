package com.cesarnorena.meli.app.presentation.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cesarnorena.meli.app.navigation.Router
import com.cesarnorena.meli.app.navigation.routes.ProductRoute
import com.cesarnorena.meli.app.presentation.StatefulViewModel
import com.cesarnorena.meli.app.presentation.search.stateful.SearchEvent
import com.cesarnorena.meli.app.presentation.search.stateful.SearchEvent.ItemClickEvent
import com.cesarnorena.meli.app.presentation.search.stateful.SearchEvent.NewSearchEvent
import com.cesarnorena.meli.app.presentation.search.stateful.SearchState
import com.cesarnorena.meli.app.presentation.search.stateful.SearchState.ErrorState
import com.cesarnorena.meli.app.presentation.search.stateful.SearchState.LoadingState
import com.cesarnorena.meli.app.presentation.search.stateful.SearchState.SearchResultState
import com.cesarnorena.meli.domain.search.SearchProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val router: Router,
    private val searchProducts: SearchProducts
) : StatefulViewModel<SearchState, SearchEvent>() {

    override val state = MutableLiveData<SearchState>()

    override fun event(event: SearchEvent) {
        when (event) {
            is NewSearchEvent -> onNewSearch(event.query)
            is ItemClickEvent -> router.navigate(ProductRoute())
        }
    }

    private fun onNewSearch(
        query: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        state.postValue(LoadingState)

        try {
            val products = searchProducts(query)
            state.postValue(SearchResultState(products))
        } catch (e: Exception) {
            state.postValue(ErrorState)
        }
    }
}
