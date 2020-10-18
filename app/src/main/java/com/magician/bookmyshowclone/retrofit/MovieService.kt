package com.magician.bookmyshowclone.retrofit


import com.magician.bookmyshowclone.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun getMovies( @Query("api_key") key: String): Call<MovieResponse>
}