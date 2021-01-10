package com.cesarnorena.meli.app.library.extensions

import androidx.test.espresso.IdlingRegistry
import com.cesarnorena.meli.app.presentation.StatefulActivity

fun StatefulActivity<*, *>.registerIdle() {
    IdlingRegistry.getInstance().register(idleResource)
}

fun StatefulActivity<*, *>.unregisterIdle() {
    IdlingRegistry.getInstance().unregister(idleResource)
}
