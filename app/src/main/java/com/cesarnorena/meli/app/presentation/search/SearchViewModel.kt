package com.cesarnorena.meli.app.presentation.search

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
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
import com.cesarnorena.meli.data.search.model.SearchItem
import com.cesarnorena.meli.domain.search.SearchProducts
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel @ViewModelInject constructor(
    @Assisted private val savedState: SavedStateHandle,
    private val router: Router,
    private val searchProducts: SearchProducts
) : StatefulViewModel<SearchState, SearchEvent>() {

    override val state = MutableLiveData<SearchState>()

    private var searchItems: List<SearchItem>?
        get() = savedState.getLiveData<List<SearchItem>>("search-items").value
        set(value) {
            savedState["search-items"] = value
        }

    init {
        searchItems?.let {
            state.value = SearchResultState(it)
        }
    }

    override fun event(event: SearchEvent) {
        when (event) {
            is NewSearchEvent -> onNewSearch(event.query)
            is ItemClickEvent -> router.navigate(ProductRoute(event.item.id))
        }
    }

    private fun onNewSearch(
        query: String?
    ) = viewModelScope.launch(IO) {
        state.postValue(LoadingState)

        try {
            val newSearchItems = searchProducts(query ?: "")
            withContext(Main) { searchItems = newSearchItems }
            state.postValue(SearchResultState(newSearchItems))
        } catch (e: Exception) {
            state.postValue(ErrorState(e))
        }
    }
}
