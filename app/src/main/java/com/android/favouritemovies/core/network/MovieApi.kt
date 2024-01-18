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
            "$SERVER_URL/movie/"
    }

    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") pageNumber: Int
    ): ResponseDto<List<MovieResponseDto>>

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("page") pageNumber: Int
    ): ResponseDto<List<MovieResponseDto>>
}