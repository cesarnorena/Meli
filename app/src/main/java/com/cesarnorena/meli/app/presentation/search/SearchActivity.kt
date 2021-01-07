package com.cesarnorena.meli.app.presentation.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.cesarnorena.meli.app.presentation.StatefulActivity
import com.cesarnorena.meli.app.presentation.search.stateful.SearchEvent.ItemClickEvent
import com.cesarnorena.meli.app.presentation.search.stateful.SearchEvent.NewSearchEvent
import com.cesarnorena.meli.app.presentation.search.stateful.SearchState
import com.cesarnorena.meli.app.presentation.search.stateful.SearchState.ErrorState
import com.cesarnorena.meli.app.presentation.search.stateful.SearchState.LoadingState
import com.cesarnorena.meli.app.presentation.search.stateful.SearchState.SearchResultState
import com.cesarnorena.meli.databinding.ActivitySearchBinding
import com.cesarnorena.meli.library.extensions.hideKeyboard
import com.cesarnorena.meli.library.extensions.onSearchSubmit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : StatefulActivity<SearchState, SearchViewModel>() {

    private lateinit var binding: ActivitySearchBinding

    override val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchInput.onSearchSubmit {
            viewModel.event(NewSearchEvent(it))
        }

        binding.searchList.onClickListener {
            viewModel.event(ItemClickEvent(it))
        }

        viewModel.state.observe(this, ::bindState)
    }

    override fun bindState(state: SearchState) = when (state) {
        is LoadingState -> with(binding) {
            hideKeyboard(root.windowToken)
            progress.visibility = View.VISIBLE
        }

        is SearchResultState -> with(binding) {
            progress.visibility = View.GONE
            searchList.visibility = View.VISIBLE
            searchList.addAll(state.products)
        }

        is ErrorState -> with(binding) {
            progress.visibility = View.GONE
            // TODO: Show error state
        }
    }
}
