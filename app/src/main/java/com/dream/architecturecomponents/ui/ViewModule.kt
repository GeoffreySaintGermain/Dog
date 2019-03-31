package com.dream.architecturecomponents.ui

import com.dream.architecturecomponents.ui.movies.create.CreateDogViewModel
import com.dream.architecturecomponents.ui.movies.detail.DetailDogViewModel
import com.dream.architecturecomponents.ui.movies.list.DogViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModule = module {

    viewModel { DogViewModel(androidApplication()) }

    viewModel { CreateDogViewModel(androidApplication()) }

    viewModel { DetailDogViewModel(androidApplication()) }

}