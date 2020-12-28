package com.cesarnorena.meli.app.presentation.search

sealed class SearchEvent {
    data class NewSearchEvent(val query: String) : SearchEvent()
}