package com.example.flixsterplus

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailsActivity"

class DetailsActivity : AppCompatActivity() {

    private lateinit var posterPathImageView: ImageView
    private lateinit var releaseDateTextView: TextView
    private lateinit var popularTextView: TextView
    private lateinit var overviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Initialize the views
        posterPathImageView = findViewById(R.id.poster_path)
        releaseDateTextView = findViewById(R.id.release_date)
        popularTextView = findViewById(R.id.popularity)
        overviewTextView = findViewById(R.id.overview)

        // Retrieve the movie passed from the adapter
        val movie: Movie? = intent.getParcelableExtra(MOVIE_EXTRA)

        // Check if the movie was passed correctly
        if (movie != null) {
            // Log movie details
            Log.d(TAG, "Movie title: ${movie.original_title}")

            // Populate the views with movie data
            releaseDateTextView.text = movie.release_date ?: "N/A"
            popularTextView.text = movie.popularity ?: "N/A"
            overviewTextView.text = movie.overview ?: "No Overview Available"

            // Load the poster image using Glide
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            Glide.with(this)
                .load(imageUrl)
                .into(posterPathImageView)
        } else {
            Log.d(TAG, "No movie data received")
            // Handle the case where no movie was passed (e.g., show a default image or message)
        }
    }
}
