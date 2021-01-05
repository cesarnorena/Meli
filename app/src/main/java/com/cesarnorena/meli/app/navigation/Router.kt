package com.cesarnorena.meli.app.navigation

import android.app.Activity

interface Route {
    fun canHandle(descriptor: String): Boolean
    suspend fun navigate(activity: Activity)
}

class Router(private val activity: Activity) {
    private val routes = mutableSetOf<Route>()

    suspend fun navigate(route: Route) {
        route.navigate(activity)
    }

    suspend fun navigate(descriptor: String) {
        routes.find { it.canHandle(descriptor) }
            ?.navigate(activity)
            ?: throw IllegalArgumentException()
    }

    fun addRoutes(routes: Set<Route>): Router {
        this.routes.addAll(routes)
        return this
    }
}
