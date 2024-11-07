package com.example.flixsterplus

import android.os.Bundle
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

        val movie = intent.getSerializableExtra(MOVIE_EXTRA) as Movie

        releaseDateTextView.text = movie.release_date
        popularTextView.text = movie.popularity
        overviewTextView.text = movie.overview

        Glide.with(this)
            .load(movie.poster_path)
            .into(posterPathImageView)

    }
}