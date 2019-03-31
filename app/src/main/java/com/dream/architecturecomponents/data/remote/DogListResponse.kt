package com.dream.architecturecomponents.data.remote

import com.google.gson.annotations.SerializedName

data class DogListResponse(

    @SerializedName("status")
    val page: Int,

    @SerializedName("results")
    val results: List<DogResponse>

)