package com.android.favouritemovies.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.favouritemovies.R
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.presentation.main.component.ItemMovie
import com.android.favouritemovies.presentation.util.theme.FavouriteMoviesTheme
import com.android.favouritemovies.presentation.util.theme.BackgroundColor
import com.android.favouritemovies.presentation.util.theme.topNavigationTitle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FavouriteMoviesTheme()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FavouriteMoviesTheme {
        FavouriteMoviesTheme()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteMoviesTheme() {
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
                            stringResource(R.string.top_rated_movies),
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
        ScrollContent(innerPadding, MovieDataSource.movieList)
    }
}

@Composable
fun ScrollContent(innerPadding: PaddingValues, movies: List<Movie>) {
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
        items(movies) { movie ->
            ItemMovie(movie)
        }
    }
}

object MovieDataSource {
    val movieList: List<Movie> = listOf(
        Movie(
            "Inception",
            "https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
            8.8,
            27548,
            "2010-07-14"
        ),
        Movie(
            "The Dark Knight",
            "https://image.tmdb.org/t/p/w500/1hRoyzDtpgMU7Dz4JF22RANzQO7.jpg",
            9.0,
            26862,
            "2008-07-16"
        ),
        Movie(
            "The Shawshank Redemption",
            "https://image.tmdb.org/t/p/w500/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
            8.7,
            25344,
            "1994-09-23"
        ),
        Movie(
            "The Godfather",
            "https://image.tmdb.org/t/p/w500/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            8.7,
            19307,
            "1972-03-14"
        ),
        Movie(
            "Pulp Fiction",
            "https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg",
            8.5,
            21141,
            "1994-09-10"
        ),
        Movie(
            "Fight Club",
            "https://image.tmdb.org/t/p/w500/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg",
            8.4,
            20941,
            "1999-10-15"
        ),
        Movie(
            "Forrest Gump",
            "https://image.tmdb.org/t/p/w500/yE5d3BUhE8hCnkMUJOo1QDoOGNz.jpg",
            8.5,
            20259,
            "1994-07-06"
        ),
        Movie(
            "The Matrix",
            "https://image.tmdb.org/t/p/w500/gynBNzwyaHKtXqlEKKLioNkjKgN.jpg",
            8.1,
            16945,
            "1999-03-30"
        ),
        Movie(
            "Schindler's List",
            "https://image.tmdb.org/t/p/w500/c8Ass7acuOe4za6DhSattE359Gr.jpg",
            8.6,
            9414,
            "1993-11-30"
        ),
        Movie(
            "The Silence of the Lambs",
            "https://image.tmdb.org/t/p/w500/rplLJ2hPcOQmkFHeaM3f7zmp6lU.jpg",
            8.3,
            10139,
            "1991-01-30"
        )
    )
}