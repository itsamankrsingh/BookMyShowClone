package com.magician.bookmyshowclone.data

import com.magician.bookmyshowclone.model.MovieResponse
import com.magician.bookmyshowclone.retrofit.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {

    override fun fetchMovies(
        apiKey: String,
        onSuccess: (MovieResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        val response = movieService.getMovies(apiKey)
        response.enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    Thread {
                        onSuccess(response.body()!!)
                    }.start()
                } else {
                    onError(response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onError(t.localizedMessage ?: "Something Went Wrong")
            }
        })
    }
}