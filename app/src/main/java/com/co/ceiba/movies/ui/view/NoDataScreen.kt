package com.co.ceiba.movies.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.co.ceiba.movies.R

@Composable
fun NoDataScreen (){
    val image: Painter = painterResource(id = R.drawable.nointernet)
    Image(painter = image,contentDescription = "", modifier = Modifier.fillMaxSize())
}
