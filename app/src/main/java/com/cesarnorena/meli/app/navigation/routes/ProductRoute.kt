package com.cesarnorena.meli.app.navigation.routes

import android.app.Activity
import android.content.Intent
import com.cesarnorena.meli.app.navigation.Route
import com.cesarnorena.meli.app.presentation.detail.DetailActivity

class ProductRoute : Route {

    override fun canHandle(
        descriptor: String
    ): Boolean = descriptor.contains("meli://product")

    override suspend fun navigate(activity: Activity) {
        val intent = Intent(activity, DetailActivity::class.java)
        activity.startActivity(intent)
    }
}
