package com.co.ceiba.movies.ui.movie

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.movies.R
import java.util.*

@Composable
fun MovieTitleSection(movieInfo: Movie){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${movieInfo.title.capitalize(Locale.ROOT)}",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface
        )
        Text(
            text = "Release date ${movieInfo.release_date.capitalize(Locale.ROOT)}",
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
            contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Vote Average: ${movie.vote_average}",
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(id = R.drawable.voting),
            modifier = modifier.size(15.dp),
            tint = MaterialTheme.colors.secondary,
            contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Total Votes: ${movie.vote_count}",
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
        )
    }

}