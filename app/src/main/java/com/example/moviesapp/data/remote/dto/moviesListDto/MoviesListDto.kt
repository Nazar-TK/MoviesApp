package com.example.moviesapp.data.remote.dto.moviesListDto


import com.google.gson.annotations.SerializedName

data class MoviesListDto(
    val page: Int,
    val results: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)