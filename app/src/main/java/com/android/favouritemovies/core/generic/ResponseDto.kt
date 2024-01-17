package com.android.favouritemovies.core.generic

import com.squareup.moshi.Json

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

class ResponseDto<T : Any> {
    @field:Json(name = "results")
    val results: T? = null

    @field:Json(name = "page")
    val page: Int? = null

    @field:Json(name = "total_pages")
    val totalPages: Int? = null

    @field:Json(name = "total_results")
    val totalResults: Int? = null
}