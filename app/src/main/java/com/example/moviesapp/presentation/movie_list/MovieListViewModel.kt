package com.example.moviesapp.presentation.movie_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.core.utils.Resource
import com.example.moviesapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state: StateFlow<MovieListState> = _state

    private var currentPage = 1

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            Log.d("MovieListViewModel", "Fetching movies for page: $currentPage")
            repository.getMovies(pageNum = currentPage).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        val newMovies = result.data ?: emptyList()
                        val updatedMovies = _state.value.movies + newMovies
                        _state.value = _state.value.copy(
                            movies = updatedMovies,
                            isLoading = false
                        )
                        Log.d("MovieListViewModel", "Movies fetched: ${newMovies.size}")
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message ?: "An unexpected error occurred.",
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun loadMoreMovies() {
        currentPage++
        getMovies()
    }
}