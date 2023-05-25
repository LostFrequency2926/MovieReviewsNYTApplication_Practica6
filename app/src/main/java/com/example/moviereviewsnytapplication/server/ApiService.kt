package com.example.moviereviewsnytapplication.server

import com.example.moviereviewsnytapplication.server.model.MoviesReview
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("reviews/search.json")
    suspend fun loadReviews(@Query("api-key") apiKey: String) : MoviesReview

}