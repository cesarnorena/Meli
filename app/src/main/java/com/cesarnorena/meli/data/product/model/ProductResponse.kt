package com.cesarnorena.meli.data.product.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ProductResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Double,
    @SerializedName("currency_id") val currency: String,
    @SerializedName("pictures") val images: @RawValue List<Image>,
) : Parcelable

@Parcelize
data class Image(
    @SerializedName("secure_url") val url: String
) : Parcelable
