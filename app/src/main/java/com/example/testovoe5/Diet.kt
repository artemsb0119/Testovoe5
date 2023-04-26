package com.example.testovoe5

import com.google.gson.annotations.SerializedName

data class Diet (

    @SerializedName("priem") val priem : String,
    @SerializedName("photo") val photo : String,
    @SerializedName("food") val food : String,
    @SerializedName("kcal") val kcal : String
)
