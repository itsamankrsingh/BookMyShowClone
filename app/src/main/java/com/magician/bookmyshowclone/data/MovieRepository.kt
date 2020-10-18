package com.magician.bookmyshowclone.data

import com.magician.bookmyshowclone.data.local.entity.MovieResponse

interface MovieRepository {
    fun fetchMovies(
        apiKey: String,
        onSuccess: (MovieResponse) -> Unit,
        onError: (String) -> Unit
    )

    fun getMoviesLocal(onSuccess: (MovieResponse?) -> Unit)
}