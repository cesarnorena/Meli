package com.cesarnorena.meli.app.presentation.search.stateful

import com.cesarnorena.meli.app.presentation.Event
import com.cesarnorena.meli.data.search.model.SearchItem

sealed class SearchEvent : Event {
    data class NewSearchEvent(val query: String?) : SearchEvent()
    data class ItemClickEvent(val item: SearchItem) : SearchEvent()
}
