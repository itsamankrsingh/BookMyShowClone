package com.magician.bookmyshowclone.data

import com.magician.bookmyshowclone.model.MovieResponse

interface MovieRepository {
    fun fetchMovies(
        apiKey: String,
        onSuccess: (MovieResponse) -> Unit,
        onError: (String) -> Unit
    )
}