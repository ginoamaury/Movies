package com.co.ceiba.movies.ui.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.movies.R
import com.co.ceiba.movies.ui.movie.MovieAboutSection
import com.co.ceiba.movies.ui.movie.MovieTitleSection
import com.co.ceiba.movies.ui.movie.MovieVoteSection
import com.co.ceiba.movies.viewmodel.MovieViewModel
import com.google.accompanist.coil.rememberCoilPainter


@Composable
fun DescriptionScreen (navController: NavController, idMovie: Int?, movieViewModel: MovieViewModel = hiltViewModel()){
    movieViewModel.getMovie(idMovie?:0)
    val uiState by movieViewModel.uiState.collectAsState()
    Surface(Modifier.fillMaxSize()) {
        MovieDetailScreen(loading = uiState.loading , movie = uiState.success , error = uiState.error, navController = navController)
    }
}

@Composable
fun MovieDetailScreen(
    movie : Movie?,
    loading: Boolean,
    error: Boolean,
    navController: NavController,
    topPadding: Dp = 20.dp,
    movieImageSize: Dp = 200.dp,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 16.dp)
    ) {
        MovieDetailTopSection(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter)
        )
        MovieDetailStateWrapper(
            movie = movie,
            loading = loading,
            error = error,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding + movieImageSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .shadow(15.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.surface)
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            loadingModifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .padding(
                    top = topPadding + movieImageSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        )
        Box(contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()) {
            if(movie != null) {
                Image(
                    painter = rememberCoilPainter(
                        request = "https://image.tmdb.org/t/p/w500"+movie.backdrop_path,
                        previewPlaceholder = R.drawable.tree_logo,
                    ),
                    contentDescription = movie.overview,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(movieImageSize)
                        .offset(y = topPadding),
                )
            }
        }
    }
}

@Composable
fun MovieDetailTopSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black,
                        Color.Transparent
                    )
                )
            )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(36.dp)
                .offset(16.dp, 16.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
    }
}

@Composable
fun MovieDetailStateWrapper(
    movie : Movie?,
    modifier: Modifier = Modifier,
    loading: Boolean,
    error: Boolean,
    loadingModifier: Modifier = Modifier
) {
    if(loading){
        CircularProgressIndicator(
            color = MaterialTheme.colors.primary,
            modifier = loadingModifier
        )
    } else {
        if (error) {
            NoDataScreen()
        } else {
            if (movie != null) {

                MovieDetailSection(
                    movieInfo = movie,
                    modifier = modifier
                        .offset(y = (-20).dp)
                )


            }
        }
    }
}

@Composable
fun MovieDetailSection(
    movieInfo: Movie,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 100.dp)
            .verticalScroll(scrollState)
    ) {
        MovieTitleSection(movieInfo = movieInfo)
        Spacer(modifier = Modifier.height(8.dp))
        MovieVoteSection(movie = movieInfo)
        Spacer(modifier = Modifier.height(8.dp))
        MovieAboutSection(movie = movieInfo)
    }
}


