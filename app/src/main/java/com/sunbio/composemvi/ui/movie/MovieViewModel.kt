package com.sunbio.composemvi.ui.movie

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunbio.composemvi.api.MovieItem
import com.sunbio.composemvi.data.MovieRemoteDataSource
import com.sunbio.composemvi.data.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository(MovieRemoteDataSource())
    var uiState by mutableStateOf(MovieUiState())
        private set

    fun fetchLastedMovies() {
        viewModelScope.launch {
            uiState = uiState.copy(
                movies =
                repository.fetchLastedMovie("Douban", 0, 10)
            )

        }
    }

}

data class MovieUiState(
    val movies: List<MovieItem>? = null
)