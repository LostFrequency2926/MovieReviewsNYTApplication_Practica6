package com.example.moviereviewsnytapplication.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moviereviewsnytapplication.local.model.LocalReview

@Dao
interface ReviewDAO {

    @Insert
    suspend fun saveReview(movie: LocalReview)

    @Delete
    suspend fun deleteReview(movie: LocalReview)

    @Query("SELECT * FROM table_reviews WHERE display_title LIKE :display_title")
    suspend fun searchReview(display_title: String): LocalReview

    @Query("SELECT * FROM table_reviews")
    suspend fun loadFavoritesReviews(): MutableList<LocalReview>

}