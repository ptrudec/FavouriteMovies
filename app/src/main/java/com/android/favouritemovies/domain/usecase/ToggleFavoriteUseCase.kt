package com.android.favouritemovies.domain.usecase

import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.domain.repository.FavoriteRepository
import javax.inject.Inject

/**
 * Created by petar.tomorad-rudec on 18/01/2024
 */

class ToggleFavoriteUseCase @Inject constructor(private val repository: FavoriteRepository) {
    suspend operator fun invoke(movie: Movie) {
        repository.toggleFavoriteMovie(movie)
    }
}