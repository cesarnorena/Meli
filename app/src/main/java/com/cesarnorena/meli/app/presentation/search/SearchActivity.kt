package com.cesarnorena.meli.app.presentation.search

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.cesarnorena.meli.app.presentation.search.SearchEvent.NewSearchEvent
import com.cesarnorena.meli.app.presentation.search.SearchState.SearchResultState
import com.cesarnorena.meli.databinding.ActivitySearchBinding
import com.cesarnorena.meli.domain.SearchProducts
import com.cesarnorena.meli.library.extensions.hideKeyboard
import com.cesarnorena.meli.library.extensions.onSearchAction

class SearchViewModelFactory : Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = modelClass
        .getConstructor(SearchProducts::class.java)
        .newInstance(SearchProducts())
}

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory() }

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

    private fun bindState(state: SearchState) {
        if (state is SearchResultState) {
            val product = state.products.firstOrNull()
            if (product != null) {
                Toast.makeText(this, state.products.first(), Toast.LENGTH_LONG).show()
            }
        }
    }
}
