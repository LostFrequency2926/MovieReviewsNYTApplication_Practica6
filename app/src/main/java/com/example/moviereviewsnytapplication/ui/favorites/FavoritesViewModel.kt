package com.example.moviereviewsnytapplication.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereviewsnytapplication.local.model.LocalReview
import com.example.moviereviewsnytapplication.local.repository.LocalReviewRepository
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    private val localReviewRepository = LocalReviewRepository()
    private val _favoriteReviews: MutableLiveData<ArrayList<LocalReview>> = MutableLiveData()
    val favoriteReviews: LiveData<ArrayList<LocalReview>> = _favoriteReviews

    fun loadFavoriteMovies() {
        viewModelScope.launch {
            val listFavoritesReviews = localReviewRepository.loadFavoritesReviews()
            _favoriteReviews.postValue(listFavoritesReviews as ArrayList<LocalReview>)
        }
    }

    fun deleteFavoriteReview(localMovie: LocalReview) {
        viewModelScope.launch {
            localReviewRepository.deleteFavoriteReview(localMovie)
        }
    }
}