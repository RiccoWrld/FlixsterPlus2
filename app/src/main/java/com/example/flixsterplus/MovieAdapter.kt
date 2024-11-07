package com.example.flixsterplus

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val TAG = "MovieAdapter"

class MovieAdapter (private val context: Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        val movie = movies[position]
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

                private val movieImageView = itemView.findViewById<ImageView>(R.id.poster_path)
                private val movieTitle = itemView.findViewById<TextView>(R.id.original_title)

                init {
                    itemView.setOnClickListener(this)
                }

                override fun onClick(v: View) {
                    val movie = movies[absoluteAdapterPosition]

                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra(MOVIE_EXTRA, movie)
                    context.startActivity(intent)
                }

                fun bind(movie: Movie){
                    movieTitle.text = movie.original_title

                    Glide.with(context)
                        .load(movie.poster_path)
                        .into(movieImageView)
                }

    }


}