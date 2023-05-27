package com.example.moviereviewsnytapplication.server.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Link(
    @SerializedName("suggested_link_text")
    val suggestedLinkText: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
): Serializable