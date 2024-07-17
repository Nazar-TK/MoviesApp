package com.example.moviesapp.domain.repository

import com.example.moviesapp.core.utils.Resource
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MovieInfo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(pageNum: Int = 1): Flow<Resource<List<Movie>>>

    fun getCoinDetails(movieId: Int): Flow<Resource<MovieInfo>>
}