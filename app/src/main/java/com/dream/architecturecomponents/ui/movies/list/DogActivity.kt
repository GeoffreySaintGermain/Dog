package com.dream.architecturecomponents.ui.movies.list

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.model.Dog
import com.dream.architecturecomponents.data.remote.DogResponseCallback
import com.dream.architecturecomponents.extension.showAction
import com.dream.architecturecomponents.extension.showError
import com.dream.architecturecomponents.extension.startAnimatedActivity
import com.dream.architecturecomponents.ui.base.BaseActivity
import com.dream.architecturecomponents.ui.dog.detail.DetailDogActivity
import com.dream.architecturecomponents.ui.movies.create.CreateDogActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class DogActivity : BaseActivity<DogViewModel, ActivityDogBinding>() {

    override val layout: Int = R.layout.activity_dog

    override val viewModel: DogViewModel by viewModel()

    private var moviesAdapter = DogAdapter(this)

    override fun initView(savedInstanceState: Bundle?) {
        setupAdapter()
        setupFab()
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    private fun setupAdapter() {
        viewModel.movies.observe(this, Observer {
            moviesAdapter.submitList(it)
        })

        moviesAdapter.apply {
            onClick = { startAnimatedActivity(intentFor<DetailDogActivity>("id" to it.id)) }
            onLongClick = { showDeletePopup(it) }
        }
    }

    private fun setupFab() {
        binding.fab.onClick { startAnimatedActivity(intentFor<CreateDogActivity>()) }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this@DogActivity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@DogActivity)
            adapter = moviesAdapter
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.apply {

            fun refresh() {
                isRefreshing = true
                viewModel.refresh(object: DogResponseCallback {
                    override fun onSuccess() {
                        binding.root.showAction(getString(R.string.movies_loaded))
                        isRefreshing = false
                    }

                    override fun onError(throwable: Throwable) {
                        binding.root.showError(getString(R.string.movies_loading_error))
                        isRefreshing = false
                    }
                })
            }

            setOnRefreshListener { refresh() }
            post { refresh() }
        }
    }

    private fun showDeletePopup(movie: Dog) {
        alert(getString(R.string.delete_movie_warning, "")) {
            yesButton { viewModel.delete(movie) }
            noButton { }
        }.show()
    }
}
