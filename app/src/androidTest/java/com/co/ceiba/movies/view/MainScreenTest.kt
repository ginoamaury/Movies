package com.co.ceiba.movies.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.co.ceiba.domain.builder.Builder
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.movies.MainPage
import com.co.ceiba.movies.ui.theme.MoviesTheme
import com.co.ceiba.movies.ui.view.HomeContent
import com.co.ceiba.movies.ui.view.MovieDetailScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val movies = Builder.getListMovie()

    @Test
    fun showMainScreen_whenIsLoading_isDisplayed(){
        //Arrange
        val loading = true
        val error = false
        //Act
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeContent(movies,loading = loading,error = error, navigateToDescriptionScreen = {})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithTag(MainPage.mainScreen).assertIsDisplayed()
    }

    @Test
    fun showMainScreen_whenIsLoaded_isDisplayed(){
        //Arrange
        val loading = false
        val error = false
        //Act
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeContent(movies,loading = loading,error = error, navigateToDescriptionScreen = {})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithTag(MainPage.mainScreen).assertIsDisplayed()
    }

    @Test
    fun showMainScreen_whenHasError_isDisplayed(){
        //Arrange
        val loading = false
        val error = true
        //Act
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeContent(movies,loading = loading,error = error, navigateToDescriptionScreen = {})
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithTag(MainPage.mainScreen).assertIsDisplayed()
    }

}