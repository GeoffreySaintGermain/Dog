package com.dream.architecturecomponents.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import com.dream.architecturecomponents.BR
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.ui.movies.list.DogActivity
import com.dream.architecturecomponents.ui.movies.list.MoviesActivity

abstract class BaseActivity<V: AndroidViewModel, B: ViewDataBinding>: AppCompatActivity() {

    protected abstract val layout: Int

    protected abstract val viewModel: V

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout)
        binding.setVariable(BR.viewModel, viewModel)
        binding.setLifecycleOwner(this)

        initView(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            ActivityCompat.finishAfterTransition(this)
            true
        }
        else -> { super.onOptionsItemSelected(item) }
    }

    override fun finish() {
        super.finish()
        if (this !is DogActivity) overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    protected abstract fun initView(savedInstanceState: Bundle?)

}