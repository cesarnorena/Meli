package com.cesarnorena.meli.library.glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

interface ImageLoader {
    fun load(context: Context, view: ImageView, url: String)
}

class GlideLoader : ImageLoader {

    override fun load(context: Context, view: ImageView, url: String) {
        Glide.with(context).load(url).into(view)
    }
}
