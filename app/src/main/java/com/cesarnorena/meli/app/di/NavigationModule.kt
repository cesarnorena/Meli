package com.cesarnorena.meli.app.di

import android.app.Activity
import com.cesarnorena.meli.app.navigation.Router
import com.cesarnorena.meli.app.navigation.routes.ProductRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {

    @Provides
    fun provideRouter(
        activity: Activity,
    ): Router {
        val routes = setOf(ProductRoute())
        return Router(activity).addRoutes(routes)
    }
}
