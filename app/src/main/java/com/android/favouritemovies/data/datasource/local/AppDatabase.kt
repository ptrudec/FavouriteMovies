package com.android.favouritemovies.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.favouritemovies.domain.model.Movie

/**
 * Created by petar.tomorad-rudec on 18/01/2024
 */
@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMoviesDao(): FavoriteMoviesDao
}