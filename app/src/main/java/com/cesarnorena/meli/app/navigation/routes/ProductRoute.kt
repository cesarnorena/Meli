package com.cesarnorena.meli.app.navigation.routes

import android.app.Activity
import android.content.Intent
import com.cesarnorena.meli.app.navigation.Route
import com.cesarnorena.meli.app.presentation.detail.DetailActivity

class ProductRoute(private val id: String) : Route {

    override fun navigate(activity: Activity) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id", id)
        activity.startActivity(intent)
    }
}
