package com.sunbio.composemvi.ui.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sunbio.composemvi.api.Movie
import com.sunbio.composemvi.data.MovieRepository

class MovieViewModel : ViewModel() {
    var uiState by mutableStateOf(MovieUiState())
        private set

    fun fetchLastedMovies() = MovieRepository.getPagingData().cachedIn(viewModelScope)
}

data class MovieUiState(
    val movies: List<Movie>? = null
)