package com.co.ceiba.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.co.ceiba.movies.ui.theme.MoviesTheme
import com.co.ceiba.movies.ui.view.DescriptionScreen
import com.co.ceiba.movies.ui.view.MainScreen
import com.co.ceiba.movies.ui.view.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            this.window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    var loadingSplashScreen by remember {
                        mutableStateOf(true)
                    }
                    Navigation(
                        isLoading = { loadingSplashScreen = it },
                        loadingSplashScreen = loadingSplashScreen
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoviesTheme {

    }
}

@Composable
fun Navigation(isLoading: (Boolean) -> Unit, loadingSplashScreen: Boolean) {
    if (loadingSplashScreen) {
        SplashScreen(isLoading = isLoading)
    } else {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "main_screen") {
            composable("main_screen") {
                MainScreen(navigateToDescriptionScreen = {
                    navController.navigate("description_screen/${it}")
                })
            }
            composable("description_screen/{movieId}",
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.IntType
                    }
                )) {
                val movieId = remember {
                    it.arguments?.getInt("movieId")
                }
                DescriptionScreen(idMovie = movieId, popBackStack = {
                    navController.popBackStack()
                })
            }
        }
    }


}










