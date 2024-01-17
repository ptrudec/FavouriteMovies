package com.android.favouritemovies.core.network

import com.android.favouritemovies.core.generic.dto.ResponseDto
import com.android.favouritemovies.data.model.remote.dto.response.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

interface MovieApi {
    companion object {
        const val SERVER_URL = "https://api.themoviedb.org/3"
        const val API_URL =
            "$SERVER_URL/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&without_genres=99,10755&vote_count.gte=200"
    }

    @GET("vote_average.desc")
    suspend fun getMovies(
        @Query("page") pageNumber: Int
    ): ResponseDto<List<MovieResponseDto>>
}