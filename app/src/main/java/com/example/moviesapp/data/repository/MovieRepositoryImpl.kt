package com.example.moviesapp.data.repository

import android.util.Log
import com.example.moviesapp.core.utils.Resource
import com.example.moviesapp.data.remote.TheMovieDBApi
import com.example.moviesapp.data.remote.dto.movieInfoDto.toMovieInfo
import com.example.moviesapp.data.remote.dto.moviesListDto.toMovie
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.domain.model.MovieInfo
import com.example.moviesapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MovieRepositoryImpl(private val api: TheMovieDBApi) : MovieRepository {
    override fun getMovies(pageNum: Int): Flow<Resource<List<Movie>>> = flow {

        Log.d("HERE!", "fun getMovies() MovieRepositoryImpl")
        try {
            emit(Resource.Loading())
            val movies = api.getTopRatedMovies(page = pageNum).results.map { it.toMovie() }
            emit(Resource.Success(movies))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Could not reach the server. Check your Internet connection."))
        }
    }

    override fun getMovieInfo(movieId: String): Flow<Resource<MovieInfo>> = flow {

        Log.d("HERE!", "fun getMovieInfo() MovieRepositoryImpl")
        try {
            emit(Resource.Loading())
            val coinDescription = api.getMovieInfoById(movieId).toMovieInfo()
            emit(Resource.Success(coinDescription))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Could not reach the server. Check your Internet connection."))
        }
    }
}