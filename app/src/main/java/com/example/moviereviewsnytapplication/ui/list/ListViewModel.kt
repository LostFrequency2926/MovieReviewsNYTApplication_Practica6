package com.example.moviereviewsnytapplication.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereviewsnytapplication.server.model.MoviesReview
import com.example.moviereviewsnytapplication.server.model.Review
import com.example.moviereviewsnytapplication.server.repository.ReviewsRepository
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    val reviewsRespository = ReviewsRepository()

    private val _reviewsLoaded : MutableLiveData<ArrayList<Review>> = MutableLiveData()
    val reviewsLoaded: LiveData<ArrayList<Review>> = _reviewsLoaded

    fun loadReviews() {
        viewModelScope.launch {
            val reviewsList: MoviesReview = reviewsRespository.loadReviews()
            _reviewsLoaded.postValue(reviewsList.reviews as ArrayList<Review>)
        }
    }
}