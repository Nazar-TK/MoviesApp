package com.example.moviesapp.presentation.movie_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.core.utils.Resource
import com.example.moviesapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch() {
//            Log.d("HERE!", "getMovies launch")
//            repository.getMovies().onEach { result ->
//                when (result) {
//                    is Resource.Success -> {
//                        Log.d("HERE!", "getMovies Success")
//                        _state.value = MovieListState(movies = result.data ?: emptyList())
//                    }
//
//                    is Resource.Loading -> {
//                        Log.d("HERE!", "getMovies Loading")
//                        _state.value = MovieListState(error = result.message ?: "Something went wrong.")
//                    }
//
//                    is Resource.Error -> {
//                        Log.d("HERE!", "getMovies Error")
//                        _state.value = MovieListState(isLoading = true)
//                    }
//                }
//            }
//        }
            Log.d("HERE!", "getMovies Launch")
            repository.getMovies().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = MovieListState(movies = result.data ?: emptyList())
                        Log.d("HERE!", "getMovies Success")
                        Log.d("HERE!", "getMovies Success ${result.data?.get(0)?.posterUrl ?: ""}")
                    }

                    is Resource.Loading -> {
                        _state.value = MovieListState(isLoading = true)
                        Log.d("HERE!", "getMovies Loading")
                    }

                    is Resource.Error -> {
                        _state.value = MovieListState(
                            error = result.message ?: "An unexpected error occurred."
                        )
                        Log.d("HERE!", "getMovies Error")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}