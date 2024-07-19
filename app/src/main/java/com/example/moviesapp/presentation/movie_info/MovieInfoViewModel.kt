package com.example.moviesapp.presentation.movie_info

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.core.utils.Constants
import com.example.moviesapp.core.utils.Resource
import com.example.moviesapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _state = mutableStateOf(MovieInfoState())
    val state: State<MovieInfoState> = _state

    init {
        savedStateHandle.get<String>(Constants.MOVIE_ID)?.let { movieID ->
            getMovieInfo(movieID)
        }
    }

    private fun getMovieInfo(movieId: String) {
        Log.d("HERE!", "getMovieInfo Launch")
        repository.getMovieInfo(movieId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MovieInfoState(movie = result.data)
                }

                is Resource.Loading -> {
                    _state.value =
                        MovieInfoState(error = result.message ?: "Something went wrong.")
                }

                is Resource.Error -> {
                    _state.value = MovieInfoState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}