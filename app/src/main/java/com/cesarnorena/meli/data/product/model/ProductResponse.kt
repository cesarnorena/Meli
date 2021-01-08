package com.cesarnorena.meli.data.search.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ProductResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("pictures") val images: @RawValue List<Image>,
) : Parcelable

@Parcelize
data class Image(
    @SerializedName("secure_url") val url: String
) : Parcelable
