package com.android.favouritemovies.domain.repository


import com.android.favouritemovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by petar.tomorad-rudec on 18/01/2024
 */

interface FavoriteRepository {
    suspend fun getFavoriteMovies(): Flow<List<Movie>>

    suspend fun toggleFavoriteMovie(movie: Movie)
}