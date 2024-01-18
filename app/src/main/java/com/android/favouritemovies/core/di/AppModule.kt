package com.android.favouritemovies.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.favouritemovies.core.network.MovieApi
import com.android.favouritemovies.data.datasource.local.AppDatabase
import com.android.favouritemovies.data.datasource.local.FavoriteMoviesDao
import com.android.favouritemovies.data.datasource.remote.MovieRemoteDataSource
import com.android.favouritemovies.data.datasource.remote.MovieRemoteDataSourceImpl
import com.android.favouritemovies.data.repository.FavoriteRepositoryImpl
import com.android.favouritemovies.data.repository.MovieRepositoryImpl
import com.android.favouritemovies.domain.repository.FavoriteRepository
import com.android.favouritemovies.domain.repository.MovieRepository
import com.android.favouritemovies.domain.usecase.GetFavoritesUseCase
import com.android.favouritemovies.domain.usecase.GetMoviesUseCase
import com.android.favouritemovies.domain.usecase.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(api: MovieApi): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(movieRemoteDataSource: MovieRemoteDataSource): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource)
    }

    @Singleton
    @Provides
    fun providesGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun providesFavoritesRepository(favoriteMoviesDao: FavoriteMoviesDao): FavoriteRepository {
        return FavoriteRepositoryImpl(favoriteMoviesDao)
    }


    @Singleton
    @Provides
    fun providesGetFavoritesUseCase(favoriteRepository: FavoriteRepository): GetFavoritesUseCase {
        return GetFavoritesUseCase(favoriteRepository)
    }

    @Singleton
    @Provides
    fun providesToggleFavoriteUseCase(favoriteRepository: FavoriteRepository): ToggleFavoriteUseCase {
        return ToggleFavoriteUseCase(favoriteRepository)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideFavoriteMoviesDao(database: AppDatabase): FavoriteMoviesDao {
        return database.favoriteMoviesDao()
    }
}