package com.example.moviesapp.data.remote.dto.movieInfoDto


import com.example.moviesapp.domain.model.MovieInfo
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

fun MovieInfoDto.toMovieInfo(): MovieInfo {
    return MovieInfo(
        id = this.id,
        backdropUrl = "https://image.tmdb.org/t/p" + this.backdropPath,
        originalTitle = this.originalTitle,
        tagline = this.tagline,
        genres = this.genres.joinToString(", ") { it.name },
        budget = this.budget,
        revenue = this.revenue,
        releaseDate = this.releaseDate,
        runtime = this.runtime,
        voteAverage = this.voteAverage,
        overview = this.overview
    )
}