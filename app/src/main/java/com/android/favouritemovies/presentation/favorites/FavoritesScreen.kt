package com.android.favouritemovies.presentation.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.favouritemovies.R
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.presentation.home.component.MovieList
import com.android.favouritemovies.presentation.home.component.MoviesState
import com.android.favouritemovies.presentation.util.route.AppScreen
import com.android.favouritemovies.presentation.util.theme.BackgroundColor
import com.android.favouritemovies.presentation.util.theme.topNavigationTitle

/**
 * Created by petar.tomorad-rudec on 19/01/2024
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {
    val movieListState by viewModel.favoriteMovies.collectAsState()
    val movieList: List<Movie> = movieListState

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
                            stringResource(R.string.favorite_movies),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.topNavigationTitle,
                            color = Color.White
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        MovieList(innerPadding, MoviesState.ListMovie(movieList), viewModel = viewModel)
    }
}