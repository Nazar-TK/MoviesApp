package com.example.moviesapp.di

import com.example.moviesapp.data.remote.TheMovieDBApi
import com.example.moviesapp.data.repository.MovieRepositoryImpl
import com.example.moviesapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTheMovieDBApi(): TheMovieDBApi {
        return Retrofit.Builder()
            .baseUrl(TheMovieDBApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: TheMovieDBApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}