package com.example.moviesapp.data.remote

import com.example.moviesapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.THE_MOVIE_DB_API_KEY)
            .build()
        val requestWithApiKey = originalRequest.newBuilder().url(url).build()
        return chain.proceed(requestWithApiKey)
    }
}