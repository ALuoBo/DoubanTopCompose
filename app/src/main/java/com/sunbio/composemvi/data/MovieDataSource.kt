package com.sunbio.composemvi.data

import com.sunbio.composemvi.api.ApiService
import com.sunbio.composemvi.api.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataSource {

}

class MovieRemoteDataSource {
    private val api = ApiService
    private val ioDispatcher = Dispatchers.IO

    suspend fun fetchLatestMovies(type: String, skip: Int, limit: Int): List<MovieItem> =
        withContext(ioDispatcher) {
            api.getTop(type, skip, limit)
        }

}