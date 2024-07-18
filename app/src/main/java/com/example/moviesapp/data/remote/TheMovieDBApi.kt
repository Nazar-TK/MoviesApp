package com.example.moviesapp.data.remote

import com.example.moviesapp.data.remote.dto.movieInfoDto.MovieInfoDto
import com.example.moviesapp.data.remote.dto.moviesListDto.MovieDto
import com.example.moviesapp.data.remote.dto.moviesListDto.MoviesListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBApi {

    @GET("top_rated?language=en-US")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int = 1,
        ): MoviesListDto

    @GET("{movieId}?language=en-US")
    suspend fun getMovieInfoById(
        @Path("movieId") movieId: Int,
    ) : MovieInfoDto


    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
    }
}