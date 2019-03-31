package com.dream.architecturecomponents.ui.dog.detail

import android.os.Bundle
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.ui.base.BaseActivity
import com.dream.architecturecomponents.ui.movies.detail.DetailDogViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailDogActivity : BaseActivity<DetailDogViewModel, ActivityDetailDogBinding>() {

    override val layout: Int = R.layout.activity_detail_dog

    override val viewModel: DetailDogViewModel by viewModel()

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.DogId.value = intent.getIntExtra("id", 0)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
