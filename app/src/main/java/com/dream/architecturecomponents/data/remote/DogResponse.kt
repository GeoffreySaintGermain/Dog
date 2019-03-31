package com.dream.architecturecomponents.data.remote

import com.google.gson.annotations.SerializedName

data class DogResponse(

    @SerializedName("status")
    val status: String,

    @SerializedName("message")
    val image: String,

    @SerializedName("id")
    val id: Int
)