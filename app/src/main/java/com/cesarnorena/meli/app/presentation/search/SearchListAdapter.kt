package com.cesarnorena.meli.app.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cesarnorena.meli.R
import com.cesarnorena.meli.databinding.ListItemSearchBinding

class SearchListAdapter(
    private val list: MutableList<String>
) : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: ListItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_search, parent, false)
        return ViewHolder(ListItemSearchBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val item = list[position]
        binding.productTitle.text = item
    }

    override fun getItemCount(): Int = list.size

    fun addAll(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
