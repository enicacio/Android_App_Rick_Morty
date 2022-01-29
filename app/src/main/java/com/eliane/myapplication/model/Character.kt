package com.eliane.myapplication.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: Origin,
        // val name: String,
        // val url: String
    @SerializedName("location") val location: Location,
        // val name: String,
        // val url: String
    @SerializedName("image") val image: String,
    @SerializedName("url") val url: String,
    //val created: Date,


)