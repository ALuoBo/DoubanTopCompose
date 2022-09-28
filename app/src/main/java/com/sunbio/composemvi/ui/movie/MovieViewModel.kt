package com.sunbio.composemvi.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sunbio.composemvi.data.MovieRepository

class MovieViewModel : ViewModel() {
    fun fetchLastedMovies() = MovieRepository.getPagingData().cachedIn(viewModelScope)
}

