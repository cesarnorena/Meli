package com.cesarnorena.meli.app.injection

import android.app.Activity
import com.cesarnorena.meli.app.navigation.Router
import com.cesarnorena.meli.app.navigation.routes.ProductRoute
import com.cesarnorena.meli.library.glide.GlideLoader
import com.cesarnorena.meli.library.glide.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideRouter(
        activity: Activity,
    ): Router {
        val routes = setOf(ProductRoute())
        return Router(activity).addRoutes(routes)
    }

    @Provides
    fun provideImageLoader(): ImageLoader = GlideLoader()
}
