package com.android.favouritemovies.presentation.home

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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.favouritemovies.R
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.presentation.util.theme.BackgroundColor
import com.android.favouritemovies.presentation.util.theme.topNavigationTitle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.res.painterResource
import com.android.favouritemovies.presentation.home.component.MovieList
import com.android.favouritemovies.presentation.home.component.MoviesState
import com.android.favouritemovies.presentation.util.route.AppScreen

/**
 * Created by petar.tomorad-rudec on 19/01/2024
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
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
                    IconButton(onClick = { navController.navigate(AppScreen.FavoritesScreen.route) }) {
                        Icon(
                            painter = painterResource(R.drawable.star_full),
                            tint = Color.White,
                            contentDescription = "Localized description"
                        )
                    }
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
        MovieList(innerPadding, MoviesState.Paged(moviePagingItems), viewModel = viewModel)
    }
}