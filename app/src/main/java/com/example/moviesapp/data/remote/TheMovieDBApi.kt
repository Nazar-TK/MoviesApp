package com.example.moviesapp.data.remote

import com.example.moviesapp.data.remote.dto.movieInfoDto.MovieInfoDto
import com.example.moviesapp.data.remote.dto.moviesListDto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBApi {

    @GET("top_rated?language=en-US")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
        ): List<MovieDto>

    @GET("{movieId}?language=en-US")
    suspend fun getMovieInfoById(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ) : MovieInfoDto


    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"

    }
}