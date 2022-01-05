package com.co.ceiba.movies.ui.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.co.ceiba.domain.models.Movie

@Composable
fun MovieAboutSection(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "Resume",
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.overview,
            color = MaterialTheme.colors.onSurface,
            fontSize = 12.sp,
            textAlign = TextAlign.Justify
        )
    }
}