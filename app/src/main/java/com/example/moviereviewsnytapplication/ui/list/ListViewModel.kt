package com.example.moviereviewsnytapplication.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereviewsnytapplication.server.model.MoviesReview
import com.example.moviereviewsnytapplication.server.model.Review
import com.example.moviereviewsnytapplication.server.repository.ReviewsRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ListViewModel : ViewModel() {

    val reviewsRespository = ReviewsRepository()

    private val _reviewsLoaded : MutableLiveData<ArrayList<Review>> = MutableLiveData()
    val reviewsLoaded: LiveData<ArrayList<Review>> = _reviewsLoaded

    fun loadReviews() {
        viewModelScope.launch {
            try {
                val reviewsList: MoviesReview = reviewsRespository.loadReviews()
                _reviewsLoaded.postValue(reviewsList.reviews as ArrayList<Review>)
            } catch (e: HttpException) {
                // Manejar el error HTTP aquí
                // Por ejemplo, mostrar un mensaje de error al usuario o tomar alguna acción específica
                // Puedes acceder a información adicional sobre el error, como el código de respuesta HTTP, utilizando e.code()
                // e.message() también proporciona un mensaje de error general

                // Ejemplo: mostrar un mensaje de error al usuario
                val errorMessage = "Error en la solicitud: ${e.code()}: ${e.message()}"
                // Puedes utilizar una variable MutableLiveData para mostrar el mensaje en la interfaz de usuario
                // errorLiveData.postValue(errorMessage)
            } catch (e: Exception) {
                // Manejar otros errores aquí
            }
        }
    }
}