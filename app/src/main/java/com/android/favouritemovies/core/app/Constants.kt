package com.android.favouritemovies.core.app

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

object Constants {
    private const val TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NmU1ZmY4NWRkZDVkYTU1NzQ4M2QxN2Q0N2Q1MjQ1ZSIsInN1YiI6IjY1YTY1NDQ3YzUyNWM0MDEyYmY4YzUxMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mEweuYqgd4sgdrYjvJphYnccNmRgH5a9ELAv_5LolAE"
    const val MOVIE_API_TOKEN =
        "Bearer $TOKEN"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    const val MAX_PAGE_SIZE = 10
    const val FAVORITE_MOVIES = "favorite_movies"
}