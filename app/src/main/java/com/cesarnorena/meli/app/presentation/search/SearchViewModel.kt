package com.cesarnorena.meli.app.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesarnorena.meli.app.presentation.search.SearchEvent.NewSearchEvent
import com.cesarnorena.meli.app.presentation.search.SearchState.SearchResultState
import com.cesarnorena.meli.domain.SearchProducts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchProducts: SearchProducts
) : ViewModel() {

    val state = MutableLiveData<SearchState>()

    fun event(searchEvent: SearchEvent) {
        if (searchEvent is NewSearchEvent) onNewSearch(searchEvent.query)
    }

    private fun onNewSearch(
        query: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        val products = searchProducts(query)
        state.postValue(SearchResultState(products))
    }
}
