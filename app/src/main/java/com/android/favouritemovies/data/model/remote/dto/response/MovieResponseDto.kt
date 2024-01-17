package com.android.favouritemovies.data.model.remote.dto.response

import com.squareup.moshi.Json

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

data class MovieResponseDto(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "original_title")
    val originalTitle: String?,

    @field:Json(name = "poster_path")
    val posterPath: String?,

    @field:Json(name = "vote_average")
    val voteAverage: Double,

    @field:Json(name = "vote_count")
    val voteCount: Int,

    @field:Json(name = "release_date")
    val releaseDate: String?
)
