package com.cesarnorena.meli.app.injection

import android.app.Activity
import com.cesarnorena.meli.app.navigation.Router
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
    ): Router = Router(activity)
}
