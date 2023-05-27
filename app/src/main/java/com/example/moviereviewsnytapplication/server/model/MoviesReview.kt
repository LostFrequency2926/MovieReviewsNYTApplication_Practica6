package com.example.moviereviewsnytapplication.server.model


import com.google.gson.annotations.SerializedName

data class MoviesReview(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("has_more")
    val hasMore: Boolean?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    val reviews: List<Review?>?,
    @SerializedName("status")
    val status: String?
)