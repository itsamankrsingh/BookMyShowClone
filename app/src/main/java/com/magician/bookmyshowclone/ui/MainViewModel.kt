package com.magician.bookmyshowclone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magician.bookmyshowclone.data.MovieRepository
import com.magician.bookmyshowclone.data.local.entity.MovieResponse
import com.magician.bookmyshowclone.util.NetworkHelper

class MainViewModel(
    private val networkHelper: NetworkHelper,
    private val movieRepository: MovieRepository
) : ViewModel() {

    companion object {
        private const val API_KEY = "832b6fb5b53da04cde95f0bee9c3bd50"
        private const val SOMETHING_WENT_WRONG = "Something went wrong"
    }

    private val _movieResponse = MutableLiveData<MovieResponse>()
    val movieResponse: LiveData<MovieResponse>
        get() = _movieResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String>
        get() = _errorResponse

    fun onCreate() {
        if (networkHelper.isNetworkConnected()) {
            movieRepository.fetchMovies(API_KEY, { movieResponse ->
                _movieResponse.postValue(movieResponse)
            }, { errorMessage ->
                _errorResponse.postValue(errorMessage)

            })
        } else {
            movieRepository.getMoviesLocal { movieResponse ->
                if (movieResponse != null && movieResponse.results.isNotEmpty()) {
                    _movieResponse.postValue(movieResponse)
                } else {
                    _errorResponse.postValue(SOMETHING_WENT_WRONG)
                }
            }
        }
    }
}