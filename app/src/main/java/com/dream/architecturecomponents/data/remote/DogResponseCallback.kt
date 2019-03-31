package com.dream.architecturecomponents.data.remote

interface DogResponseCallback {

    fun onSuccess()

    fun onError(throwable: Throwable)
}