package com.magician.bookmyshowclone.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.magician.bookmyshowclone.data.MovieRepository
import com.magician.bookmyshowclone.ui.MainViewModel
import com.magician.bookmyshowclone.util.NetworkHelper

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val networkHelper: NetworkHelper,
    private val movieRepository: MovieRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(networkHelper, movieRepository) as T
    }
}