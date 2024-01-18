package com.android.favouritemovies.domain.repository

import androidx.paging.PagingData
import com.android.favouritemovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

interface MovieRepository {
    suspend fun getTopRatedMovies(): Flow<PagingData<Movie>>

    suspend fun getPopularMovies(): Flow<PagingData<Movie>>
}