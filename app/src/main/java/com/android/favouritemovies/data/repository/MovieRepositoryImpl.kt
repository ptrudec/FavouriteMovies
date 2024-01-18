package com.android.favouritemovies.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.favouritemovies.core.app.Constants
import com.android.favouritemovies.data.datasource.remote.MovieRemoteDataSource
import com.android.favouritemovies.data.repository.paging.MoviePagingSource
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

class MovieRepositoryImpl @Inject constructor(private val remoteDataSource: MovieRemoteDataSource) :
    MovieRepository {
    private fun getMovies(fetchPopular: Boolean): Pager<Int, Movie> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.MAX_PAGE_SIZE,
                prefetchDistance = 2
            ),
            pagingSourceFactory = { MoviePagingSource(remoteDataSource, fetchPopular) }
        )
    }

    override suspend fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return getMovies(fetchPopular = false).flow
    }

    override suspend fun getPopularMovies(): Flow<PagingData<Movie>> {
        return getMovies(fetchPopular = true).flow
    }
}