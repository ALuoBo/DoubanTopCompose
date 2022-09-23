package com.sunbio.composemvi.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sunbio.composemvi.api.ApiService
import com.sunbio.composemvi.api.Movie
import com.sunbio.composemvi.api.MovieItem
import com.sunbio.composemvi.api.asEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataSource : PagingSource<Int, Movie>() {
    private val movieRemoteDataSource = MovieRemoteDataSource()
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        Log.d("MovieDataSource", "load")
        return try {
            val page = params.key ?: 0
            val pageSize = params.loadSize
            val movies =
                movieRemoteDataSource.fetchLatestMovies(page, pageSize).map { it.asEntry() }
            val nextKey = if (movies.isNotEmpty() && page < 250) page + pageSize else null
            val prevKey = if (page > pageSize) page - pageSize else null
            Log.d("MovieDataSource", "next: $nextKey")
            LoadResult.Page(movies, prevKey, nextKey)
        } catch (e: Exception) {
            throw e
            LoadResult.Error(e)
        }
    }

}

class MovieRemoteDataSource {
    private val api = ApiService
    private val ioDispatcher = Dispatchers.IO

    suspend fun fetchLatestMovies(skip: Int, limit: Int): List<MovieItem> =
        withContext(ioDispatcher) {
            api.getTop(skip, limit)
        }

}