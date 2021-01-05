package com.cesarnorena.meli.app.presentation.detail

import androidx.lifecycle.MutableLiveData
import com.cesarnorena.meli.app.presentation.StatefulViewModel

class DetailViewModel : StatefulViewModel<DetailState, DetailEvent>() {

    override val state = MutableLiveData<DetailState>()

    override fun event(event: DetailEvent) {}
}
