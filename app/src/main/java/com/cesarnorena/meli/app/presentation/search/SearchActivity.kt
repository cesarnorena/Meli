package com.cesarnorena.meli.app.presentation.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.cesarnorena.meli.app.presentation.StatefulActivity
import com.cesarnorena.meli.app.presentation.search.SearchEvent.NewSearchEvent
import com.cesarnorena.meli.app.presentation.search.SearchState.LoadingState
import com.cesarnorena.meli.app.presentation.search.SearchState.SearchResultState
import com.cesarnorena.meli.databinding.ActivitySearchBinding
import com.cesarnorena.meli.library.extensions.hideKeyboard
import com.cesarnorena.meli.library.extensions.onSearchAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : StatefulActivity<SearchState, SearchViewModel>() {

    private lateinit var binding: ActivitySearchBinding

    override val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchInput.onSearchAction {
            viewModel.event(NewSearchEvent(it))
        }

        with(binding.searchList) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(DividerItemDecoration(this@SearchActivity, VERTICAL))
            adapter = SearchListAdapter(mutableListOf())
        }

        viewModel.state.observe(this, ::bindState)
    }

    override fun bindState(state: SearchState) = when (state) {
        is LoadingState -> {
            hideKeyboard(binding.root.windowToken)
            // TODO: Show loading
        }

        is SearchResultState -> {
            // TODO: Dismiss loading
            (binding.searchList.adapter as SearchListAdapter).addAll(state.products)
        }
    }
}
