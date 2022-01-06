package com.co.ceiba.movies.ui.movie

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.movies.R
import java.util.*

@Composable
fun MovieTitleSection(movieInfo: Movie) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.testTag(stringResource(id = R.string.title_section_tag))
    ) {
        Text(
            text = movieInfo.title.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = "${stringResource(id = R.string.release_date)} ${movieInfo.releaseDate.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }}",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun MovieVoteSection(
    movie: Movie,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.star),
            modifier = modifier.size(15.dp),
            tint = MaterialTheme.colors.secondary,
            contentDescription = stringResource(id = R.string.icon_star_description)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${stringResource(id = R.string.vote_average)}: ${movie.voteAverage}",
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(id = R.drawable.voting),
            modifier = modifier.size(15.dp),
            tint = MaterialTheme.colors.secondary,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${stringResource(id = R.string.total_votes)}: ${movie.voteCount}",
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
        )
    }

}