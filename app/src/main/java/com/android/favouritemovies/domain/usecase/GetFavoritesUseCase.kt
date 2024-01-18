package com.android.favouritemovies.domain.usecase

import com.android.favouritemovies.core.generic.usecase.BaseUseCase
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by petar.tomorad-rudec on 18/01/2024
 */

class GetFavoritesUseCase @Inject constructor(private val repository: FavoriteRepository):
    BaseUseCase<Unit, Flow<List<Movie>>> {
    override suspend fun execute(input: Unit): Flow<List<Movie>> {
        return repository.getFavoriteMovies()
    }
}