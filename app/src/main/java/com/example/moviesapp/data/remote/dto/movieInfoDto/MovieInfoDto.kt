package com.example.moviesapp.data.remote.dto.movieInfoDto


import com.google.gson.annotations.SerializedName

data class MovieInfoDto(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val tagline: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
)