package com.cesarnorena.meli.app.presentation.detail.stateful

import com.cesarnorena.meli.app.presentation.Event

sealed class DetailEvent : Event {
    data class ProductEvent(val id: String) : DetailEvent()
}
