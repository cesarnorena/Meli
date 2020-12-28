package com.cesarnorena.meli.app.presentation.search

sealed class SearchState {
    data class SearchResultState(val products: List<String>) : SearchState()
}
