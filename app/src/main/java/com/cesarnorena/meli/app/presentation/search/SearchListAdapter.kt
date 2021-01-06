package com.cesarnorena.meli.app.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cesarnorena.meli.data.search.model.SearchItem
import com.cesarnorena.meli.databinding.ListItemSearchBinding
import com.cesarnorena.meli.library.glide.GlideLoader
import com.cesarnorena.meli.library.glide.ImageLoader
import java.text.NumberFormat
import java.util.Locale

class SearchListAdapter(
    private val list: MutableList<SearchItem>
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    private val imageLoader: ImageLoader = GlideLoader()

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

        val formatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"))
        productPrice.text = formatter.format(item.price)
    }

    override fun getItemCount(): Int = list.size

    fun addAll(newList: List<SearchItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
