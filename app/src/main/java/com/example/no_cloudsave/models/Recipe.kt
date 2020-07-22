package com.example.no_cloudsave

import com.google.gson.annotations.SerializedName

data class Recipe (
    @SerializedName("image") val image : String,
    @SerializedName("name")  val name : String,
    @SerializedName("definition")  val definition: String
)

