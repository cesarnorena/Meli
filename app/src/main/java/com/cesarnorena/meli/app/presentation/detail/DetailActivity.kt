package com.cesarnorena.meli.app.presentation.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.cesarnorena.meli.app.presentation.StatefulActivity
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailEvent.ProductEvent
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState.ErrorSate
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState.LoadingState
import com.cesarnorena.meli.app.presentation.detail.stateful.DetailState.ProductState
import com.cesarnorena.meli.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : StatefulActivity<DetailState, DetailViewModel>() {

    private lateinit var binding: ActivityDetailBinding

    override val viewModel: DetailViewModel by viewModels()

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
            }
            is ErrorSate -> {
            }
            is ProductState -> with(binding) {
                val product = state.product
                Glide.with(this@DetailActivity)
                    .load(product.images.first().url)
                    .into(productImage)
                productTitle.text = product.title
                productPrice.text = product.price.toString()
            }
        }
    }
}
