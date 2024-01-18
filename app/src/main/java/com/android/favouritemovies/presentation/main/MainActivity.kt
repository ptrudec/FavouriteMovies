package com.android.favouritemovies.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.favouritemovies.R
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.presentation.main.component.ErrorMessage
import com.android.favouritemovies.presentation.main.component.ItemMovie
import com.android.favouritemovies.presentation.main.component.LoadingNextPageItem
import com.android.favouritemovies.presentation.main.component.LoadingPage
import com.android.favouritemovies.presentation.util.theme.BackgroundColor
import com.android.favouritemovies.presentation.util.theme.topNavigationTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FavouriteMoviesTheme(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteMoviesTheme(viewModel: MainViewModel) {
    val moviePagingItems: LazyPagingItems<Movie> = viewModel.moviesState.collectAsLazyPagingItems()
    var expanded by remember { mutableStateOf(false) }
    var selectedDropdownItem by remember { mutableIntStateOf(R.string.top_rated_movies) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.height(48.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundColor,
                    titleContentColor = BackgroundColor,
                ),
                title = {
                    Box(Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                        Text(
                            stringResource(selectedDropdownItem),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.topNavigationTitle,
                            color = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        expanded = !expanded
                    }) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            tint = Color.White,
                            contentDescription = stringResource(id = R.string.filter)
                        )
                    }
                    if (expanded) {
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }) {
                            DropdownMenuItem(onClick = {
                                expanded = false
                                viewModel.clearMovies()
                                viewModel.getTopRatedMovies()
                                selectedDropdownItem = R.string.top_rated_movies
                            }) {
                                Text(text = stringResource(id = R.string.top_rated))
                            }
                            DropdownMenuItem(onClick = {
                                expanded = false
                                viewModel.clearMovies()
                                viewModel.getPopularMovies()
                                selectedDropdownItem = R.string.popular_movies
                            }) {
                                Text(text = stringResource(id = R.string.popular))
                            }
                        }
                    }
                }
            )
        },
    ) { innerPadding ->

        ScrollContent(innerPadding, moviePagingItems, viewModel = viewModel)
    }
}

@Composable
fun ScrollContent(
    innerPadding: PaddingValues,
    movies: LazyPagingItems<Movie>,
    viewModel: MainViewModel
) {
    LazyColumn(
        modifier = Modifier.background(BackgroundColor),
        contentPadding = PaddingValues(
            start = 24.dp,
            end = 24.dp,
            top = innerPadding.calculateTopPadding() + 24.dp,
            bottom = innerPadding.calculateBottomPadding() + 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(movies.itemCount) { index ->
            ItemMovie(
                item = movies[index]!!, viewModel
            )
        }
        movies.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        LoadingPage(modifier = Modifier.fillParentMaxSize())
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = movies.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    val error = movies.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
    }
}