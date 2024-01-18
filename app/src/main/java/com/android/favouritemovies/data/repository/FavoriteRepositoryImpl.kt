package com.android.favouritemovies.data.repository

import com.android.favouritemovies.data.datasource.local.FavoriteMoviesDao
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by petar.tomorad-rudec on 18/01/2024
 */

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteMoviesDao: FavoriteMoviesDao
) : FavoriteRepository {
    override suspend fun getFavoriteMovies(): Flow<List<Movie>> {
        return favoriteMoviesDao.getFavoriteMovies()
    }

    override suspend fun toggleFavoriteMovie(movie: Movie) {
        val existingFavorite = favoriteMoviesDao.getFavoriteMovieById(movie.id)

        if (existingFavorite != null) {
            favoriteMoviesDao.deleteFavoriteMovie(existingFavorite)
        } else {
            favoriteMoviesDao.insertFavoriteMovie(movie)
        }
    }
}