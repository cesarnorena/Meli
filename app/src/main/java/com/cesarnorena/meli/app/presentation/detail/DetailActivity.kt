package com.cesarnorena.meli.app.presentation.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.cesarnorena.meli.app.presentation.StatefulActivity
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
    }

    override fun bindState(state: DetailState) {
        TODO("Not yet implemented")
    }
}
