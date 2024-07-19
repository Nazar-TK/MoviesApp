package com.example.moviesapp.data.remote.dto.moviesListDto


import com.example.moviesapp.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
)

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = this.id,
        originalTitle = this.originalTitle,
        posterUrl = "https://image.tmdb.org/t/p/w300" + this.posterPath,
        releaseYear = this.releaseDate.take(4), // Extracting the year from release date
        voteAverage = this.voteAverage
    )
}