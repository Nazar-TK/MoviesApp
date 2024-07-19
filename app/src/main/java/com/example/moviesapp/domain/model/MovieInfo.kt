package com.example.moviesapp.domain.model

data class MovieInfo(
    val id: Int,
    val backdropUrl: String,
    val originalTitle: String,
    val tagline: String,
    val genres: String,
    val budget: Int,
    val revenue: Int,
    val releaseDate: String,
    val runtime: Int,
    val voteAverage: Double,
    val overview: String,
)
