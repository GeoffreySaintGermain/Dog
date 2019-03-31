package com.dream.architecturecomponents.ui.movies.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dream.architecturecomponents.data.DogRepository
import com.dream.architecturecomponents.data.model.Dog
import com.dream.architecturecomponents.data.remote.DogResponseCallback
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class DogViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val movieRepository: DogRepository by inject()

    var movies: LiveData<List<Dog>> = movieRepository.getAll()

    fun delete(movie: Dog) {
        movieRepository.delete(movie)
    }

    fun refresh(callback: DogResponseCallback) {
        movieRepository.downloadDog(callback)
    }
}