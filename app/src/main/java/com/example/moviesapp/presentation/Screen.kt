package com.example.moviesapp.presentation

sealed class Screen(val route: String) {
    object MovieListScreen: Screen("movie_list_screen")
    object MovieInfoScreen: Screen("movie_info_screen")
}