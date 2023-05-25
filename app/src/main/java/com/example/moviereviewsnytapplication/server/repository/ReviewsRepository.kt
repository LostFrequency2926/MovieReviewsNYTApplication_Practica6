package com.example.moviereviewsnytapplication.server.repository

import com.example.moviereviewsnytapplication.server.MovieReviewsNYT

class ReviewsRepository {

    private val apikey = "98IUtJah2dsmfMXX8GnqmBnbXxrAmWDW"

    suspend fun loadReviews() = MovieReviewsNYT.retrofit.loadReviews(apikey)
}