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
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Headers

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val  TAG = "MainActivity"
private val MOVIE_SEARCH_URL =
    "https://api.themoviedb.org/3/discover/movie?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"


class MainActivity : AppCompatActivity() {
    private val movies = mutableListOf<Movie>()
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        moviesRecyclerView = findViewById(R.id.movies)
        val movieAdapter = MovieAdapter(this, movies)
        moviesRecyclerView.adapter = movieAdapter

        moviesRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            moviesRecyclerView.addItemDecoration(dividerItemDecoration)
        }

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

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.i(TAG, "Successfully fetched movies: $json")
                try {
                    // Check if the activity is finishing or destroyed before attempting UI updates
                    if (isFinishing || isDestroyed) {
                        Log.e(TAG, "Activity is finishing or destroyed. Skipping UI updates.")
                        return
                    }

                    // Get the raw JSON string from the response
                    val jsonString = json?.toString()
                    if (jsonString == null) {
                        Log.e(TAG, "Failed to parse JSON, jsonString is null")
                        return
                    }

                    // Log the raw JSON for debugging purposes
                    Log.i(TAG, "Raw JSON: $jsonString")

                    // Use Kotlin Serialization to decode the JSON string into SearchNewsResponse
                    val parsedJson = createJson().decodeFromString<SearchNewsResponse>(jsonString)

                    // Add the results to the movie list and notify the adapter
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