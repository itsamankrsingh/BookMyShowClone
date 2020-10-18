package com.magician.bookmyshowclone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.magician.bookmyshowclone.R
import com.magician.bookmyshowclone.data.MovieRepositoryImpl
import com.magician.bookmyshowclone.model.Movie
import com.magician.bookmyshowclone.model.MovieResponse
import com.magician.bookmyshowclone.retrofit.RetrofitBuilder
import com.magician.bookmyshowclone.ui.adapter.MovieAdapter
import com.magician.bookmyshowclone.ui.util.MainViewModelFactory
import com.magician.bookmyshowclone.util.NetworkHelper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.movieResponse.observe(this, Observer { movieResponse ->
            showMovies(movieResponse.results)
            hideProgress()
        })

        viewModel.errorResponse.observe(this, Observer { errorMessage ->
            showErrorMessage(errorMessage)
            hideProgress()
        })
    }

    private fun setupViewModel() {
        showProgress()
        viewModel = ViewModelProvider(
            this, MainViewModelFactory(
                NetworkHelper(this),
                MovieRepositoryImpl(RetrofitBuilder.buildService())
            )
        )[MainViewModel::class.java]
        viewModel.onCreate()
    }


    private fun showMovies(movies: List<Movie>) {
        recycler_view.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
        recycler_view.setHasFixedSize(true)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = MovieAdapter(movies as ArrayList<Movie>)
    }

    private fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    private fun showErrorMessage(errorMessage: String?) {
        error_view.visibility = View.VISIBLE
        error_view.text = errorMessage
    }
}