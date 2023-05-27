package com.example.moviereviewsnytapplication.local.repository

import com.example.moviereviewsnytapplication.MovieReviewsNyt
import com.example.moviereviewsnytapplication.local.ReviewDAO
import com.example.moviereviewsnytapplication.local.model.LocalReview

class LocalReviewRepository {

    suspend fun saveReview(localMovie: LocalReview) {
        val reviewDAO: ReviewDAO = MovieReviewsNyt.database.ReviewDAO()
        reviewDAO.saveReview(localMovie)
    }

    suspend fun searchReview(display_title: String): LocalReview {
        val reviewDAO: ReviewDAO = MovieReviewsNyt.database.ReviewDAO()
        return reviewDAO.searchReview(display_title)
    }

    suspend fun loadFavoritesReviews(): MutableList<LocalReview> {
        val reviewDAO: ReviewDAO = MovieReviewsNyt.database.ReviewDAO()
        return reviewDAO.loadFavoritesReviews()
    }

    suspend fun deleteFavoriteReview(localMovie: LocalReview) {
        val reviewDAO: ReviewDAO = MovieReviewsNyt.database.ReviewDAO()
        reviewDAO.deleteReview(localMovie)
    }

}