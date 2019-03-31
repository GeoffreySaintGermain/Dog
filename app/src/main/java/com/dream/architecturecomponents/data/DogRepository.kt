package com.dream.architecturecomponents.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.dream.architecturecomponents.data.locale.DogDao
import com.dream.architecturecomponents.data.model.Dog
import com.dream.architecturecomponents.data.remote.DogResponse
import com.dream.architecturecomponents.data.remote.DogResponseCallback
import com.dream.architecturecomponents.data.remote.DogService
import com.dream.architecturecomponents.extension.toDate
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*

class DogRepository: KoinComponent {

    private val DogDao: DogDao by inject()

    private val service = DogService.create()

    fun insertAll(Dog: List<Dog>) = doAsync {
        DogDao.insertAll(Dog)
        Log.d("DogRepository","inserting Dog: $Dog")
    }

    fun insert(Dog: Dog) =
        insertAll(listOf(Dog))

    fun delete(Dog: Dog) = doAsync { DogDao.delete(Dog) }

    fun getById(id: Int): LiveData<Dog> = DogDao.getById(id)

    fun getAll(): LiveData<List<Dog>> = DogDao.getAllLive()


    fun downloadDog(callback: DogResponseCallback) {
        service.getTopRatedDog()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { DogListResponse -> insertAll(DogListResponse.results.map { DogResponse -> DogResponseToDog(DogResponse) }) },
                onComplete = { callback.onSuccess() },
                onError = { callback.onError(it) }
            )
    }

    fun downloadDogWithExtraInfos(callback: DogResponseCallback) {
        service.getTopRatedDog()
            .subscribeOn(Schedulers.io())
            .flatMap { DogListResponse -> Observable.fromIterable(DogListResponse.results) }
            .flatMap { DogShortResponse -> service.getDog(DogShortResponse.id) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { DogFullResponse -> insert(DogResponseToDog(DogFullResponse)) },
                onComplete = { callback.onSuccess() },
                onError = { callback.onError(it) }
            )
    }

    private fun DogResponseToDog(DogResponse: DogResponse): Dog =
        Dog(
            image = DogResponse.image
        )
}