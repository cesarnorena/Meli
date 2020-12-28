package com.cesarnorena.meli.data.site

interface SiteRepository {
    suspend fun get(): String
}

class DefaultSiteRepository : SiteRepository {
    override suspend fun get(): String {
        // TODO Getting the site_id is important, but for now it will be hardcoded
        // MLB = brazilian site_id
        return "MLB"
    }
}
