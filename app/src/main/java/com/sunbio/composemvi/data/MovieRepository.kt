package com.sunbio.composemvi.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sunbio.composemvi.api.Movie
import kotlinx.coroutines.flow.Flow

object MovieRepository {

    fun getPagingData(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                initialLoadSize = 50,
            ),
            pagingSourceFactory = { MovieDataSource() }
        ).flow
    }

}