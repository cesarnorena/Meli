package com.cesarnorena.meli.app.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

interface Event

interface State

abstract class StatefulViewModel<S : State, E : Event> : ViewModel() {
    abstract val state: MutableLiveData<S>
    abstract fun event(event: E)
}
