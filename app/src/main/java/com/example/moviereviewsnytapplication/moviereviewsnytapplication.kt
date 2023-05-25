package com.example.moviereviewsnytapplication

import android.app.Application
import androidx.room.Room
import com.example.moviereviewsnytapplication.local.ReviewDataBase

class MovieReviewsNytApplication: Application() {

    companion object{
        lateinit var database: ReviewDataBase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            ReviewDataBase::class.java,
            "review_db"
        ).build()
    }

}