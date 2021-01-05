package com.cesarnorena.meli.app.presentation.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cesarnorena.meli.app.navigation.Router
import com.cesarnorena.meli.app.navigation.routes.ProductRoute
import com.cesarnorena.meli.app.presentation.StatefulViewModel
import com.cesarnorena.meli.app.presentation.search.SearchEvent.NewSearchEvent
import com.cesarnorena.meli.app.presentation.search.SearchState.SearchResultState
import com.cesarnorena.meli.domain.SearchProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val router: Router,
    private val searchProducts: SearchProducts
) : StatefulViewModel<SearchState, SearchEvent>() {

    override val state = MutableLiveData<SearchState>()

    override fun event(event: SearchEvent) {
        if (event is NewSearchEvent) onNewSearch(event.query)
    }

    private fun onNewSearch(
        query: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val products = searchProducts(query)
        state.postValue(SearchResultState(products))

        router.navigate(ProductRoute())
    }
}
