package com.sunbio.composemvi.data

import com.sunbio.composemvi.api.MovieItem

class MovieRepository(private val movieDataSource: MovieRemoteDataSource) {
    suspend fun fetchLastedMovie(type: String, skip: Int, limit: Int): List<MovieItem> =
        movieDataSource.fetchLatestMovies(type, skip, limit)
}