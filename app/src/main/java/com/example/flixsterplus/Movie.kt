package com.example.flixsterplus


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class SearchNewsResponse(
    @SerializedName("results")
    val results: List<Movie>
) : Parcelable

@Parcelize
data class Movie(
    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val release_date: String?,

    @SerializedName("popularity")
    val popularity: String?,

    @SerializedName("original_title")
    val original_title: String?,

    @SerializedName("poster_path")
    val poster_path: String?) : Parcelable





