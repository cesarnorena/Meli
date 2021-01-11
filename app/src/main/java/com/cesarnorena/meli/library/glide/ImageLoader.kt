package com.cesarnorena.meli.library.glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

interface ImageLoader {
    fun load(context: Context, view: ImageView, url: String)
}

class GlideImageLoader : ImageLoader {

    private var requestManager: RequestManager? = null

    override fun load(context: Context, view: ImageView, url: String) {
        requestManager(context).load(url).into(view)
    }

    private fun requestManager(context: Context): RequestManager {
        return requestManager ?: Glide.with(context).apply {
            requestManager = this
        }
    }
}
