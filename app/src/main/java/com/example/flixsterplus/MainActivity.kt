package com.example.flixsterplus

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixsterplus.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.Headers

private const val TAG = "MainActivity"
private val MOVIE_SEARCH_URL =
    "https://api.themoviedb.org/3/discover/movie?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"


class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<Movie>()
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate view and bind
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set up RecyclerView
        moviesRecyclerView = findViewById(R.id.movies)
        val movieAdapter = MovieAdapter(this, movies)
        moviesRecyclerView.adapter = movieAdapter

        // Set RecyclerView layout manager and divider
        moviesRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            moviesRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        // Initialize AsyncHttpClient
        val client = AsyncHttpClient()
        client.get(MOVIE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch movies: $statusCode")
            }

            // onSuccess: JSON parsing and updating RecyclerView
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.i(TAG, "Successfully fetched movies: $json")
                try {
                    // Check if activity is still alive before updating the UI
                    if (isFinishing || isDestroyed) {
                        Log.e(TAG, "Activity is finishing or destroyed. Skipping UI updates.")
                        return
                    }

                    // Convert JSON to string
                    val jsonString = json?.jsonObject?.toString()


                    // Log raw JSON for debugging purposes
                    Log.i(TAG, "Raw JSON: $jsonString")

                    // Parse JSON into SearchNewsResponse object using Gson
                    val parsedJson = Gson().fromJson(jsonString, SearchNewsResponse::class.java)

                    // Add parsed movies to the list and update RecyclerView
                    parsedJson.results?.let { list ->
                        movies.addAll(list)
                        movieAdapter.notifyDataSetChanged()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Exception while parsing JSON: $e")
                }
            }
        })
    }
}







