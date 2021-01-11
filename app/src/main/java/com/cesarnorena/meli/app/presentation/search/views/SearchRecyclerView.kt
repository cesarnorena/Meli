package com.cesarnorena.meli.app.presentation.search.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cesarnorena.meli.app.presentation.search.SearchListAdapter
import com.cesarnorena.meli.data.search.model.SearchItem
import com.cesarnorena.meli.databinding.ListItemSearchBinding

internal class SearchRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RecyclerView(context, attrs, defStyleAttr) {

    private var onLoadMore: ((lastIndex: Int) -> Unit)? = null

    init {
        val inflater = LayoutInflater.from(context)
        ListItemSearchBinding.inflate(inflater)

        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter = SearchListAdapter(mutableListOf())
    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)
        val lastIndex = layoutManager().findLastCompletelyVisibleItemPosition()
        val loadMore = lastIndex == (adapter().itemCount - 1)
        if (loadMore) onLoadMore?.invoke(lastIndex)
    }

    fun onClickListener(listener: ((item: SearchItem) -> Unit)) {
        adapter().clickListener = listener
    }

    fun onLoadMore(listener: ((lastIndex: Int) -> Unit)) {
        onLoadMore = listener
    }

    fun addAll(newList: List<SearchItem>) {
        adapter().addAll(newList)
    }

    private fun adapter() = (adapter as SearchListAdapter)

    private fun layoutManager() = (layoutManager as LinearLayoutManager)
}
