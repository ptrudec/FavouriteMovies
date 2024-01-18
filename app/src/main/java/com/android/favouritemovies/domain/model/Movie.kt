package com.android.favouritemovies.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by petar.tomorad-rudec on 16/01/2024
 */

@Entity(tableName = "favorite_movies")
data class Movie(
    @PrimaryKey
    var id: Int,
    var originalTitle: String,
    var posterPath: String,
    var voteAverage: Double,
    var voteCount: Int,
    var releaseDate: String
)
