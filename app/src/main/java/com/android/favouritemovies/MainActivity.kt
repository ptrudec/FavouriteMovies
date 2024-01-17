package com.android.favouritemovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.favouritemovies.Utils.formatDate
import com.android.favouritemovies.Utils.formatNumberWithDecimalSeparator
import com.android.favouritemovies.data.Movie
import com.android.favouritemovies.ui.theme.FavouriteMoviesTheme
import com.android.favouritemovies.ui.theme.BackgroundColor
import com.android.favouritemovies.ui.theme.Orange
import com.android.favouritemovies.ui.theme.Typography
import com.android.favouritemovies.ui.theme.itemDate
import com.android.favouritemovies.ui.theme.itemTitle
import com.android.favouritemovies.ui.theme.itemVoteAverage
import com.android.favouritemovies.ui.theme.itemVoteCount
import com.android.favouritemovies.ui.theme.topNavigationTitle

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
            MovieCard(movie)
        }
    }
}

@Composable
fun MovieCard(movie: Movie) {
    var isFavourite by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(if (isFavourite) 1f else 0.5f, label = "")

    Row(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f)) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = stringResource(R.string.movie_image_description),
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
                modifier = Modifier
                    .size(95.dp, 120.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = movie.originalTitle,
                    style = Typography.itemTitle,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = stringResource(id = R.string.likes_image),
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = movie.voteAverage.toString(),
                        style = Typography.itemVoteAverage,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Orange
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "(${formatNumberWithDecimalSeparator(movie.voteCount)})",
                        style = Typography.itemVoteCount,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = stringResource(id = R.string.calendar_image),
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = formatDate(movie.releaseDate),
                        style = Typography.itemDate,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Image(
            painter = painterResource(id = if (isFavourite) R.drawable.star_full else R.drawable.star_empty),
            contentDescription = stringResource(
                id = R.string.favourite_image_description
            ),
            modifier = Modifier
                .size(24.dp, 24.dp)
                .align(Alignment.CenterVertically)
                .clickable {
                    isFavourite = !isFavourite
                }
                .alpha(alpha)

        )
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