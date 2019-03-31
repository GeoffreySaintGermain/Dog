package com.dream.architecturecomponents.ui.movies.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.dream.architecturecomponents.R
import com.dream.architecturecomponents.data.model.Dog
import com.dream.architecturecomponents.ui.base.BaseAdapter
import com.dream.architecturecomponents.ui.base.BaseViewHolder
import com.dream.architecturecomponents.utils.OnItemClickListener

class DogAdapter(lifecycleOwner: LifecycleOwner): BaseAdapter<Dog>(lifecycleOwner) {

    override fun layoutFor(position: Int): Int = R.layout.item_movie

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Dog, *> {
        val binding: ItemDogBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return DogViewHolder(binding)
    }

    class DogViewHolder(private val binding: ItemDogBinding): BaseViewHolder<Dog, ItemDogBinding>(binding) {

        override fun bind(lifecycleOwner: LifecycleOwner, item: Dog, listener: OnItemClickListener<Dog>) {
            super.bind(lifecycleOwner, item, listener)
            binding.title.text = ""
        }
    }
}