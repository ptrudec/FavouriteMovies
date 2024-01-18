package com.android.favouritemovies.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.favouritemovies.data.datasource.remote.MovieRemoteDataSource
import com.android.favouritemovies.data.model.remote.mapper.mapFromListModel
import com.android.favouritemovies.domain.model.Movie
import okio.IOException
import retrofit2.HttpException

/**
 * Created by petar.tomorad-rudec on 17/01/2024
 */

class MoviePagingSource(
    private val remoteDataSource: MovieRemoteDataSource,
    private val fetchPopular: Boolean
) :
    PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val movies =
                if (fetchPopular) remoteDataSource.getPopularMovies(pageNumber = currentPage) else remoteDataSource.getMovies(
                    pageNumber = currentPage
                )
            LoadResult.Page(
                data = movies.results!!.mapFromListModel(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.results.isEmpty()) null else movies.page!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}