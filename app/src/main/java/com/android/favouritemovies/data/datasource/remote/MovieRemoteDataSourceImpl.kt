package com.android.favouritemovies.data.datasource.remote

import com.android.favouritemovies.core.generic.dto.ResponseDto
import com.android.favouritemovies.core.network.MovieApi
import com.android.favouritemovies.data.model.remote.dto.response.MovieResponseDto
import javax.inject.Inject

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

class MovieRemoteDataSourceImpl @Inject constructor(private val api: MovieApi) :
    MovieRemoteDataSource {
    override suspend fun getMovies(pageNumber: Int): ResponseDto<List<MovieResponseDto>> {
        return api.getMovies(pageNumber = pageNumber)
    }
}