package com.cesarnorena.meli.app.presentation.search

import com.cesarnorena.meli.app.presentation.Event

sealed class SearchEvent : Event {
    data class NewSearchEvent(val query: String) : SearchEvent()
}
