package com.android.favouritemovies.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.favouritemovies.core.app.Constants.FAVORITE_MOVIES
import com.android.favouritemovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by petar.tomorad-rudec on 18/01/2024
 */

@Dao
interface FavoriteMoviesDao {
    @Query("SELECT * FROM $FAVORITE_MOVIES")
    fun getFavoriteMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM favorite_movies WHERE id = :movieId")
    suspend fun getFavoriteMovieById(movieId: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovie: Movie)

    @Delete
    suspend fun deleteFavoriteMovie(favoriteMovie: Movie)
}