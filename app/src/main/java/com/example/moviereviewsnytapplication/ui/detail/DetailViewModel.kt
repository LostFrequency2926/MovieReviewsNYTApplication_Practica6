package com.example.moviereviewsnytapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereviewsnytapplication.local.model.LocalReview
import com.example.moviereviewsnytapplication.local.repository.LocalReviewRepository
import com.example.moviereviewsnytapplication.server.model.Review
import kotlinx.coroutines.launch
class DetailViewModel : ViewModel() {

    private val localReviewRepository = LocalReviewRepository()

    private val _isReviewFavorite : MutableLiveData<Boolean> = MutableLiveData()
    val isReviewFavorite : LiveData<Boolean> = _isReviewFavorite

    fun saveReview(review: Review) {
        val localReview = LocalReview(
            displayTitle = review.displayTitle,
            reviewer = review.byline,
            publicationDate = review.publicationDate,
            imgSource = review.multimedia?.src,
            summaryShort = review.summaryShort,
            articleUrl = review.link?.url
        )

        viewModelScope.launch {
            localReviewRepository.saveReview(localReview)
        }
    }

    fun searchReview(display_title: String?) {

        var reviewFavorite = false

        viewModelScope.launch {
            val localReview = display_title?.let { localReviewRepository.searchReview(it) }

            if(localReview != null)
                reviewFavorite = true
            _isReviewFavorite.postValue(reviewFavorite)
        }
    }

}