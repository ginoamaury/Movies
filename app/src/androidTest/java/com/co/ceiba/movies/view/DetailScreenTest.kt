package com.co.ceiba.movies.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.co.ceiba.domain.builder.Builder
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.movies.MainPage
import com.co.ceiba.movies.ui.theme.MoviesTheme
import com.co.ceiba.movies.ui.view.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val movie = Builder.getMovie()

    @Test
    fun showDetailScreen_whenIsLoading_isDisplayed(){
        //Arrange
        val loading = true
        val error = false
        //Act
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MovieDetailScreen(movie,loading = loading,error = error, popBackStack = {})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithTag(MainPage.detailScreen).assertIsDisplayed()
    }

    @Test
    fun showDetailScreen_whenIsLoaded_isDisplayed(){
        //Arrange
        val loading = false
        val error = false
        //Act
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MovieDetailScreen(movie,loading = loading,error = error, popBackStack = {})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithTag(MainPage.detailScreen).assertIsDisplayed()
    }

    @Test
    fun showDetailScreen_whenHasError_isDisplayed(){
        //Arrange
        val loading = false
        val error = true
        //Act
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MovieDetailScreen(movie,loading = loading,error = error, popBackStack = {})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithTag(MainPage.detailScreen).assertIsDisplayed()
    }

}