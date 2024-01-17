package com.android.favouritemovies.core.di

import com.android.favouritemovies.core.network.MovieApi
import com.android.favouritemovies.data.datasource.remote.MovieRemoteDataSource
import com.android.favouritemovies.data.datasource.remote.MovieRemoteDataSourceImpl
import com.android.favouritemovies.data.repository.MovieRepositoryImpl
import com.android.favouritemovies.domain.repository.MovieRepository
import com.android.favouritemovies.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}