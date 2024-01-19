package com.android.favouritemovies.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.android.favouritemovies.R
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.presentation.favorites.FavoritesViewModel
import com.android.favouritemovies.presentation.favorites.component.FavoriteItemMovie
import com.android.favouritemovies.presentation.home.HomeViewModel
import com.android.favouritemovies.presentation.main.component.ErrorMessage
import com.android.favouritemovies.presentation.main.component.LoadingNextPageItem
import com.android.favouritemovies.presentation.main.component.LoadingPage
import com.android.favouritemovies.presentation.util.theme.BackgroundColor
import com.android.favouritemovies.presentation.util.theme.Typography

/**
 * Created by petar.tomorad-rudec on 19/01/2024
 */

@Composable
fun <T : ViewModel> MovieList(
    innerPadding: PaddingValues,
    moviesState: MoviesState,
    viewModel: T
) {
    Box(
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor),
            contentPadding = PaddingValues(
                start = 24.dp,
                end = 24.dp,
                top = innerPadding.calculateTopPadding() + 24.dp,
                bottom = innerPadding.calculateBottomPadding() + 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            when (val state = moviesState) {
                is MoviesState.ListMovie -> {
                    if (state.movies.isNotEmpty()) {
                        items(state.movies.size) { index ->
                            FavoriteItemMovie(item = state.movies[index], viewModel as FavoritesViewModel)
                        }
                    } else {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillParentMaxSize()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    stringResource(id = R.string.nothing_in_favorites),
                                    style = Typography.body2,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

                is MoviesState.Paged -> {
                    items(state.lazyPagingItems.itemCount) { index ->
                        HomeItemMovie(item = state.lazyPagingItems[index]!!, viewModel as HomeViewModel)
                    }

                    state.lazyPagingItems.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item {
                                    LoadingPage(modifier = Modifier.fillParentMaxSize())
                                }
                            }

                            loadState.refresh is LoadState.Error -> {
                                val error = loadState.refresh as LoadState.Error
                                item {
                                    ErrorMessage(
                                        modifier = Modifier.fillParentMaxSize(),
                                        message = error.error.localizedMessage!!,
                                        onClickRetry = { retry() }
                                    )
                                }
                            }

                            loadState.append is LoadState.Loading -> {
                                item { LoadingNextPageItem(modifier = Modifier) }
                            }

                            loadState.append is LoadState.Error -> {
                                val error = loadState.append as LoadState.Error
                                item {
                                    ErrorMessage(
                                        modifier = Modifier,
                                        message = error.error.localizedMessage!!,
                                        onClickRetry = { retry() }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class MoviesState {
    data class Paged(val lazyPagingItems: LazyPagingItems<Movie>) : MoviesState()
    data class ListMovie(val movies: List<Movie>) : MoviesState()
}