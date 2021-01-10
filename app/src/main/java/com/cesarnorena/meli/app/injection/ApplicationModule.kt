package com.cesarnorena.meli.app.injection

import com.cesarnorena.meli.BuildConfig
import com.cesarnorena.meli.data.product.DefaultProductRepository
import com.cesarnorena.meli.data.product.ProductConnector
import com.cesarnorena.meli.data.product.ProductRepository
import com.cesarnorena.meli.data.search.DefaultSearchRepository
import com.cesarnorena.meli.data.search.SearchConnector
import com.cesarnorena.meli.data.search.SearchRepository
import com.cesarnorena.meli.data.site.DefaultSiteRepository
import com.cesarnorena.meli.data.site.SiteRepository
import com.cesarnorena.meli.library.retrofit.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

@Module
@InstallIn(ApplicationComponent::class)
class BaseUrlModule {

    @Provides
    fun providesBaseUrl(): HttpUrl = BuildConfig.BASE_URL.toHttpUrl()
}

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun provideSearchConnector(
        baseUrl: HttpUrl
    ): SearchConnector = RetrofitFactory.create(baseUrl)

    @Provides
    fun provideProductConnector(
        baseUrl: HttpUrl
    ): ProductConnector = RetrofitFactory.create(baseUrl)

    @Provides
    fun provideSiteRepository(): SiteRepository = DefaultSiteRepository()

    @Provides
    fun provideSearchRepository(
        connector: SearchConnector
    ): SearchRepository = DefaultSearchRepository(connector)

    @Provides
    fun provideProductRepository(
        connector: ProductConnector
    ): ProductRepository = DefaultProductRepository(connector)
}
