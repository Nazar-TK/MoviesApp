package com.example.moviesapp.presentation.movie_list

import androidx.lifecycle.ViewModel
import com.example.moviesapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(repository: MovieRepository): ViewModel() {




}