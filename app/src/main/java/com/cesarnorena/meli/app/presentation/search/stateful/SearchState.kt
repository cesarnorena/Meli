package com.cesarnorena.meli.app.presentation.search.stateful

import com.cesarnorena.meli.app.presentation.State
import com.cesarnorena.meli.data.search.model.SearchItem

sealed class SearchState : State {
    object LoadingState : SearchState()
    object ErrorState : SearchState()
    data class SearchResultState(val products: List<SearchItem>) : SearchState()
}
