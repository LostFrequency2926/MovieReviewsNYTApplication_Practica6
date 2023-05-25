package com.example.moviereviewsnytapplication.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviereviewsnytapplication.local.model.LocalReview

@Database(entities = [LocalReview::class], version = 2)

abstract class ReviewDataBase : RoomDatabase(){

    abstract fun ReviewDAO(): ReviewDAO

}