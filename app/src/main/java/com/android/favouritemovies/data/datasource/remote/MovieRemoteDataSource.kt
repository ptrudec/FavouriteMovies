package com.android.favouritemovies.data.datasource.remote

import com.android.favouritemovies.core.generic.dto.ResponseDto
import com.android.favouritemovies.data.model.remote.dto.response.MovieResponseDto

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

interface MovieRemoteDataSource {

    suspend fun getMovies(pageNumber: Int): ResponseDto<List<MovieResponseDto>>
}