package com.cesarnorena.meli.app.presentation.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.cesarnorena.meli.app.presentation.StatefulActivity
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailEvent.ProductEvent
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState.ErrorSate
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState.LoadingState
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState.ProductState
import com.cesarnorena.meli.databinding.ActivityDetailBinding
import com.cesarnorena.meli.library.extensions.toCurrencyFormat
import com.cesarnorena.meli.library.glide.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : StatefulActivity<DetailState, DetailViewModel>() {

    private lateinit var binding: ActivityDetailBinding

    override val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.state.observe(this, ::bindState)

        val id = intent.getStringExtra("id") ?: throw IllegalArgumentException()
        viewModel.event(ProductEvent(id))
    }

    override fun bindState(state: DetailState) {
        when (state) {
            is LoadingState -> {
                // TODO: Include loading state
            }
            is ErrorSate -> {
                // TODO: Include error state
            }
            is ProductState -> with(binding) {
                val product = state.product
                imageLoader.load(this@DetailActivity, productImage, product.images.first().url)
                productTitle.text = product.title
                productPrice.text = product.price.toCurrencyFormat(product.currency)
            }
        }
    }
}
