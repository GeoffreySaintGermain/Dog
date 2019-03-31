package com.dream.architecturecomponents.ui.movies.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.dream.architecturecomponents.data.DogRepository
import com.dream.architecturecomponents.data.model.Dog
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class DetailDogViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val DogRepository: DogRepository by inject()

    var DogId: MutableLiveData<Int> = MutableLiveData()

    var Dog: LiveData<Dog> = Transformations.switchMap(DogId) { id -> DogRepository.getById(id) }
}