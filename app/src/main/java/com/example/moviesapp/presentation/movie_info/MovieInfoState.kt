package com.example.moviesapp.presentation.movie_info

import com.example.moviesapp.domain.model.MovieInfo

data class MovieInfoState (
    val isLoading: Boolean = false,
    val error: String = "",
    val movie: MovieInfo? = null
)