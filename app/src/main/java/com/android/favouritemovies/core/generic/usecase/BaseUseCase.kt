package com.android.favouritemovies.core.generic.usecase

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

interface BaseUseCase<In, Out> {
    suspend fun execute(input: In): Out
}