package com.example.moviesapp.presentation.movie_list

import com.example.moviesapp.domain.model.Movie

data class MovieListState (
    val isLoading: Boolean = false,
    val error: String = "",
    val movies: List<Movie> = emptyList()
)