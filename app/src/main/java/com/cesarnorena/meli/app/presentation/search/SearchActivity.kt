package com.cesarnorena.meli.app.presentation.search

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.cesarnorena.meli.app.presentation.StatefulActivity
import com.cesarnorena.meli.app.presentation.search.SearchEvent.NewSearchEvent
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
            hideKeyboard(binding.root.windowToken)
            viewModel.event(NewSearchEvent(it))
        }

        viewModel.state.observe(this, ::bindState)
    }

    override fun bindState(state: SearchState) {
        if (state is SearchResultState) {
            val product = state.products.firstOrNull()
            if (product != null) {
                Toast.makeText(this, state.products.first(), Toast.LENGTH_LONG).show()
            }
        }
    }
}
