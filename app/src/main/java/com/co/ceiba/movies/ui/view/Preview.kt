package com.co.ceiba.movies.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@ExperimentalFoundationApi
@Composable
fun MovieListPreview() {
    LazyVerticalGrid (
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp)
    ){
        items(10){
            MovieCardPreview()
        }
    }
}


@Composable
fun MovieCardPreview(
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
    ) {

        Box(modifier = Modifier.height(200.dp)) {

            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                )) {


            }

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        }

    }

}