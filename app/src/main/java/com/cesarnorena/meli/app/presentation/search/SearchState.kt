package com.cesarnorena.meli.app.presentation.search

import com.cesarnorena.meli.app.presentation.State

sealed class SearchState : State {
    data class SearchResultState(val products: List<String>) : SearchState()
}
