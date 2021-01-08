package com.cesarnorena.meli.data.search.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SearchResponse(
    @SerializedName("results") val results: List<SearchItem>
)

@Parcelize
data class SearchItem(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Number,
    @SerializedName("thumbnail") val thumbnail: String
) : Parcelable
