package com.cesarnorena.meli.app.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cesarnorena.meli.data.search.model.SearchItem
import com.cesarnorena.meli.databinding.ListItemSearchBinding
import com.cesarnorena.meli.library.extensions.toCurrencyFormat
import com.cesarnorena.meli.library.glide.GlideImageLoader
import com.cesarnorena.meli.library.glide.ImageLoader

class SearchListAdapter(
    private val list: MutableList<SearchItem>,
    private val imageLoader: ImageLoader = GlideImageLoader()
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    var clickListener: ((item: SearchItem) -> Unit)? = null

    class ViewHolder(
        val binding: ListItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemSearchBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.binding) {
        val item = list[position]
        imageLoader.load(root.context, productImage, item.thumbnail)
        productTitle.text = item.title
        productPrice.text = item.price.toCurrencyFormat(item.currency)

        root.setOnClickListener { clickListener?.invoke(item) }
    }

    override fun getItemCount(): Int = list.size

    fun getItem(index: Int) = list[index]

    fun addAll(newList: List<SearchItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
