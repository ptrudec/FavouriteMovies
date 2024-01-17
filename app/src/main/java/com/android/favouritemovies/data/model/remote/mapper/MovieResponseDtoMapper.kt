package com.android.favouritemovies.data.model.remote.mapper

import com.android.favouritemovies.data.model.remote.dto.response.MovieResponseDto
import com.android.favouritemovies.domain.model.Movie

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

fun MovieResponseDto.mapFromEntity() = Movie(
    originalTitle = this.originalTitle.orEmpty(),
    posterPath = this.posterPath.orEmpty(),
    releaseDate = this.releaseDate.orEmpty(),
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)

fun List<MovieResponseDto>.mapFromListModel(): List<Movie> {
    return this.map {
        it.mapFromEntity()
    }
}