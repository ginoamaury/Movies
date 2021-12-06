package com.co.ceiba.movies

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.movies.ui.theme.MoviesTheme
import com.co.ceiba.movies.ui.view.*
import com.co.ceiba.movies.viewmodel.MovieUiState
import com.co.ceiba.movies.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.window.statusBarColor = ContextCompat.getColor(this,R.color.black)
            MoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Navigation()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesTheme {

    }
}

@ExperimentalFoundationApi
@Composable
fun Navigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen" ){
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }
        composable("main_screen"){
            MainScreen(navController = navController)
        }
        composable("description_screen/{movieId}",
        arguments = listOf(
            navArgument("movieId"){
                type = NavType.IntType
            }
        )){
            val movieId = remember{
                it.arguments?.getInt("movieId")
            }
            DescriptionScreen(navController = navController, idMovie = movieId )
        }
    }
}

@Composable
fun DescriptionScreen (navController: NavController, idMovie: Int?, movieViewModel: MovieViewModel = hiltViewModel()){
    movieViewModel.getMovie(idMovie?:0)
    val uiState by movieViewModel.uiState.collectAsState()
    Surface(Modifier.fillMaxSize()) {
        ShowInfoMovie(loading = uiState.loading , movie = uiState.success , error = uiState.error, navController = navController)
    }
}

@Composable
fun ShowInfoMovie(movie :Movie?,navController: NavController, loading: Boolean, error: Boolean){
    Log.d("PASS","ENTRO 0")
    if(loading){
        //TODO
    }else{
        if(error){
            //TODO
        }else{
            Log.d("PASS","ENTRO A MOSTRAR TITULO 1")
            if(movie != null){
                Box {
                    Log.d("PASS","ENTRO A MOSTRAR TITULO 2 ${movie.title}")
                    Text("HOLI ${movie.title}")
                }
            }
        }
    }
}










