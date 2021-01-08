package com.cesarnorena.meli.app.navigation

import android.app.Activity

interface Route {
    fun navigate(activity: Activity)
}

class Router(private val activity: Activity) {
    fun navigate(route: Route) {
        route.navigate(activity)
    }
}
