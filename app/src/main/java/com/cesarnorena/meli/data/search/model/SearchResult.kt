package com.cesarnorena.meli.data.search.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("results") val results: List<SearchItem>
)

data class SearchItem(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Int
)
