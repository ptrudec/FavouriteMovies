package com.android.favouritemovies.presentation.favorites.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.favouritemovies.R
import com.android.favouritemovies.core.app.Constants
import com.android.favouritemovies.core.app.Utils
import com.android.favouritemovies.core.app.Utils.roundToTwoDecimalPlaces
import com.android.favouritemovies.domain.model.Movie
import com.android.favouritemovies.presentation.favorites.FavoritesViewModel
import com.android.favouritemovies.presentation.util.theme.Orange
import com.android.favouritemovies.presentation.util.theme.Typography
import com.android.favouritemovies.presentation.util.theme.itemDate
import com.android.favouritemovies.presentation.util.theme.itemTitle
import com.android.favouritemovies.presentation.util.theme.itemVoteAverage
import com.android.favouritemovies.presentation.util.theme.itemVoteCount
import kotlinx.coroutines.flow.map

/**
 * Created by petar.tomorad-rudec on 19/01/2024
 */
@Composable

fun FavoriteItemMovie(
    item: Movie, viewModel: FavoritesViewModel
) {
    val isFavorite by viewModel.favoriteMovies
        .map { it.any { it.id == item.id } }
        .collectAsState(initial = false)

    val alpha by animateFloatAsState(if (isFavorite) 1f else 0.5f, label = "")

    Row(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f)) {
            AsyncImage(
                model = Constants.IMAGE_URL + item.posterPath,
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
                    text = item.originalTitle,
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
                        text = item.voteAverage.roundToTwoDecimalPlaces().toString(),
                        style = Typography.itemVoteAverage,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Orange
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "(${Utils.formatNumberWithDecimalSeparator(item.voteCount)})",
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
                        text = Utils.formatDate(item.releaseDate),
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
            painter = painterResource(id = if (isFavorite) R.drawable.star_full else R.drawable.star_empty),
            contentDescription = stringResource(
                id = R.string.favourite_image_description
            ),
            modifier = Modifier
                .size(24.dp, 24.dp)
                .align(Alignment.CenterVertically)
                .clickable {
                    viewModel.toggleFavorite(item)
                }
                .alpha(alpha)

        )
    }
}