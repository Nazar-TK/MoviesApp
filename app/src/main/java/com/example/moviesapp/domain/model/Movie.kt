package com.example.moviesapp.domain.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val originalTitle: String,
    val posterUrl: String,
    val releaseYear: String,
    val voteAverage: Double,
)
