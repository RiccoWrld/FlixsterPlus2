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

class MovieAdapter(private val context: Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    // Creates a new ViewHolder for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_details, parent, false)
        return ViewHolder(view)
    }

    // Binds the data (movie) to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie) // Bind the data to the view
    }

    override fun getItemCount(): Int = movies.size // Returns the size of the movie list

    // ViewHolder class to hold references to the views
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val movieImageView: ImageView = itemView.findViewById(R.id.poster_path)
        private val movieTitle: TextView = itemView.findViewById(R.id.original_title)

        init {
            itemView.setOnClickListener(this) // Set up the click listener for the item view
        }

        // This method is triggered when the item is clicked
        override fun onClick(v: View) {
            val movie = movies[absoluteAdapterPosition]
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra(MOVIE_EXTRA, movie) // Pass the clicked movie to the DetailsActivity
            }
            context.startActivity(intent)
        }

        // Binds the movie data to the views in the ViewHolder
        fun bind(movie: Movie) {
            movieTitle.text = movie.original_title // Set the movie title
            Glide.with(context) // Use Glide to load the movie poster image
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}") // Load the image URL
                .into(movieImageView) // Set the loaded image into the ImageView
        }
    }
}
