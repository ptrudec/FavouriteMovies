package com.android.favouritemovies.data

/**
 * Created by petar.tomorad-rudec on 16/01/2024
 */

data class Movie(
    var originalTitle: String,
    var posterPath: String,
    var voteAverage: Double,
    var voteCount: Int,
    var releaseDate: String
)
