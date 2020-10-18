package com.magician.bookmyshowclone.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.magician.bookmyshowclone.R
import com.magician.bookmyshowclone.model.Movie
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MovieAdapter(private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item_layout, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size


    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        }

        fun bind(movie: Movie) {
            itemView.movie_title.text = movie.title
            itemView.release_date.text = movie.releaseDate
            itemView.avg_voting.text = movie.voteAverage.toString()
            itemView.total_votes.text = movie.voteCount.toString()
            Glide.with(itemView.context).load(IMAGE_BASE_URL + movie.posterPath)
                .into(itemView.movie_poster)

        }

    }


}

