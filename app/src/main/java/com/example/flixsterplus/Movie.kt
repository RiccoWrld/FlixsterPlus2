package com.example.flixsterplus


import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class SearchNewsResponse(
    @SerialName("results")
    val results: List<Movie>
)

@Keep
@Serializable
data class Movie(
    @SerialName("overview")
    val overview: String?,

    @SerialName("release_date")
    val release_date: String?,

    @SerialName("popularity")
    val popularity: String?,

    @SerialName("original_title")
    val original_title: String?,

    @SerialName("poster_path")
    val poster_path: String?) : java.io.Serializable





