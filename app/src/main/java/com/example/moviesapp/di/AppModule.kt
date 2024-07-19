package com.example.moviesapp.di

import com.example.moviesapp.data.remote.ApiKeyInterceptor
import com.example.moviesapp.data.remote.TheMovieDBApi
import com.example.moviesapp.data.repository.MovieRepositoryImpl
import com.example.moviesapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val apiKeyInterceptor = ApiKeyInterceptor()

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideTheMovieDBApi(okHttpClient: OkHttpClient): TheMovieDBApi {
        return Retrofit.Builder()
            .baseUrl(TheMovieDBApi.BASE_URL)
            .client(okHttpClient)
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