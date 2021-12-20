package com.co.ceiba.movies.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.movies.viewmodel.MoviesViewModel
import com.google.accompanist.coil.rememberCoilPainter
import androidx.hilt.navigation.compose.hiltViewModel
import com.co.ceiba.movies.R
import com.co.ceiba.movies.ui.widget.HomeAppBar

@Composable
fun MainScreen(navController: NavController, viewModel: MoviesViewModel = hiltViewModel()){
    val uiState by viewModel.uiState.collectAsState()
    Surface(Modifier.fillMaxSize()) {
        HomeContent(loading = uiState.loading , movies = uiState.success , error = uiState.error, navController = navController)
    }
}


@Composable
fun HomeContent(movies :List<Movie>,navController: NavController, loading: Boolean, error: Boolean) {
    Column {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 1f,
                        endY = 0f
                    )
                )
        ) {
            val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)

            Spacer(
                Modifier
                    .background(appBarColor)
                    .fillMaxWidth()
            )

            HomeAppBar(
                backgroundColor = appBarColor,
                modifier = Modifier.fillMaxWidth()
            )

            if(loading){
                MovieListPreview()
            }else{
                if(error){
                    //TODO
                }else{
                    MovieList(movies = movies, navController = navController)
                }
            }

        }


    }
}

@Composable
fun MovieCard(
    movie: Movie,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable {
                navController.navigate("description_screen/${movie.id}")
            },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
    ) {

        Box(modifier = Modifier.height(200.dp)) {

            Image(
                painter = rememberCoilPainter(
                    request = "https://image.tmdb.org/t/p/w500"+movie.backdrop_path,
                    previewPlaceholder = R.drawable.tree_logo
                ),
                contentDescription = movie.overview,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

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
                contentAlignment = Alignment.BottomStart
            ) {
                Text(movie.title,style = TextStyle(color = Color.White,fontSize = 16.sp))
            }

        }

    }

}


@Composable
fun MovieList(
    movies: List<Movie>,
    navController: NavController
) {
    LazyColumn (
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.Center
    ){
        itemsIndexed(movies){
                _, item -> MovieCard(movie = item, navController = navController)
        }
    }
}

