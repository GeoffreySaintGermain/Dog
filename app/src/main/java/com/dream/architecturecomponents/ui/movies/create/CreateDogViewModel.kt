package com.dream.architecturecomponents.ui.movies.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dream.architecturecomponents.data.DogRepository
import com.dream.architecturecomponents.data.model.Dog
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class CreateDogViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val DogRepository: DogRepository by inject()

    var image: MutableLiveData<String> = MutableLiveData()

    fun insert() {
        DogRepository.insert(
            Dog(
                image = image.value?.capitalize() ?: ""
            )
        )
    }
}