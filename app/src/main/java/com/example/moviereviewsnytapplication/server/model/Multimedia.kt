package com.example.moviereviewsnytapplication.server.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Multimedia(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("src")
    val src: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("width")
    val width: Int?
): Serializable