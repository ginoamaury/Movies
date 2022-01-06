package com.co.ceiba.movies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.co.ceiba.movies.ui.view.DescriptionScreen
import com.co.ceiba.movies.ui.view.MainScreen
import com.co.ceiba.movies.ui.view.SplashScreen

private const val MAIN_SCREEN_ROUTE = "main_screen"
private const val DESCRIPTION_SCREEN_ROUTE = "description_screen"
private const val MOVIE_ID_ARGUMENT = "movieId"

@Composable
fun Navigation(isLoading: (Boolean) -> Unit, loadingSplashScreen: Boolean) {
    if (loadingSplashScreen) {
        SplashScreen(isLoading = isLoading)
    } else {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = MAIN_SCREEN_ROUTE) {
            composable(MAIN_SCREEN_ROUTE) {
                MainScreen(navigateToDescriptionScreen = {
                    navController.navigate("${DESCRIPTION_SCREEN_ROUTE}/${it}")
                })
            }
            composable("${DESCRIPTION_SCREEN_ROUTE}/{${MOVIE_ID_ARGUMENT}}",
                arguments = listOf(
                    navArgument(MOVIE_ID_ARGUMENT) {
                        type = NavType.IntType
                    }
                )) {
                val movieId = remember {
                    it.arguments?.getInt(MOVIE_ID_ARGUMENT)
                }
                DescriptionScreen(idMovie = movieId, popBackStack = {
                    navController.popBackStack()
                })
            }
        }
    }
}