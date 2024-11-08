package com.example.flixsterplus

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailsActivity : AppCompatActivity() {

    private lateinit var posterPathImageView: ImageView
    private lateinit var releaseDateTextView: TextView
    private lateinit var popularTextView: TextView
    private lateinit var overviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        posterPathImageView = findViewById(R.id.poster_path)
        releaseDateTextView = findViewById(R.id.release_date)
        popularTextView = findViewById(R.id.popularity)
        overviewTextView = findViewById(R.id.overview)

        val movie: Movie? = intent.getParcelableExtra(MOVIE_EXTRA)

        if (movie != null) {
            // Use the movie object
            Log.d("AnotherActivity", "Movie title: ${movie.original_title}")
        } else {
            Log.d("AnotherActivity", "No movie data received")
        }


        if (movie != null) {
            releaseDateTextView.text = movie.release_date
        }
        if (movie != null) {
            popularTextView.text = movie.popularity
        }
        if (movie != null) {
            overviewTextView.text = movie.overview
        }

        if (movie != null) {
            Glide.with(this)
                .load(movie.poster_path)
                .into(posterPathImageView)
        }

    }
}