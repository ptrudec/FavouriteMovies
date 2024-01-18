package com.android.favouritemovies.domain.usecase

import androidx.paging.PagingData
import com.android.favouritemovies.core.generic.usecase.BaseUseCase
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by petar.tomorad-rudec on 18/01/2024
 */

class GetPopularMoviesUseCase @Inject constructor(private val repository: MovieRepository) :
    BaseUseCase<Unit, Flow<PagingData<Movie>>> {
    override suspend fun execute(input: Unit): Flow<PagingData<Movie>> {
        return repository.getPopularMovies()
    }
}