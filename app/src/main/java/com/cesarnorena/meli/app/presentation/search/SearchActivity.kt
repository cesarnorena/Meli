package com.cesarnorena.meli.app.presentation.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.cesarnorena.meli.app.presentation.StatefulActivity
import com.cesarnorena.meli.app.presentation.search.SearchState.LoadingState
import com.cesarnorena.meli.app.presentation.search.SearchState.SearchResultState
import com.cesarnorena.meli.databinding.ActivitySearchBinding
import com.cesarnorena.meli.library.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : StatefulActivity<SearchState, SearchViewModel>() {

    private lateinit var binding: ActivitySearchBinding

    override val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSearchInput()
        setupAdapter()

        viewModel.state.observe(this, ::bindState)
    }

    override fun bindState(state: SearchState) = when (state) {
        is LoadingState -> {
            hideKeyboard(binding.root.windowToken)
            binding.progress.visibility = View.VISIBLE
        }

        is SearchResultState -> {
            binding.progress.visibility = View.GONE
            (binding.searchList.adapter as SearchListAdapter).addAll(state.products)
        }
    }

    private fun setupSearchInput() {
        binding.searchInput.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.event(SearchEvent.NewSearchEvent(it)) }
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        })
    }

    private fun setupAdapter() {
        with(binding.searchList) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(DividerItemDecoration(this@SearchActivity, VERTICAL))
            adapter = SearchListAdapter(mutableListOf())
        }
    }
}
