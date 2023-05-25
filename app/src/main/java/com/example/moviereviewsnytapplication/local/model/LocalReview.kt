package com.example.moviereviewsnytapplication.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_reviews")
data class LocalReview (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "display_title") val displayTitle: String?,
    @ColumnInfo(name = "reviewer") val reviewer: String?,
    @ColumnInfo(name = "publication_date") val publicationDate : String?,
    @ColumnInfo(name = "img_source") val imgSource: String?,
    @ColumnInfo(name = "summary_short") val summaryShort: String?,
    @ColumnInfo(name = "article_url") val articleUrl: String?
)