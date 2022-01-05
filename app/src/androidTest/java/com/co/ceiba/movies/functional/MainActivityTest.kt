package com.co.ceiba.movies.functional


import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.co.ceiba.movies.MainActivity
import com.co.ceiba.movies.MainPage
import com.co.ceiba.movies.Navigation
import com.co.ceiba.movies.ui.theme.MoviesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>(MainActivity::class.java)

    @ExperimentalTestApi
    @Test
    fun getAllMovies_whenAllIsRight_okResult() {
        //Arrange
        val showSplashScreen = false
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation({}, showSplashScreen)
                }
            }
        }
        //Act
        val list = composeTestRule.onNode(hasTestTag(MainPage.movieList), useUnmergedTree = true)
        //Assert
        list.assertIsDisplayed()
            .performScrollToIndex(2).performClick()
    }

    @ExperimentalTestApi
    @Test
    fun getAllMovies_andShowDetailScreen_okResult() {

        //Arrange
        val showSplashScreen = false
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation({}, showSplashScreen)
                }
            }
        }
        //Act
        val list = composeTestRule.onNode(hasTestTag(MainPage.movieList), useUnmergedTree = true)
        //Assert
        list.assertIsDisplayed()
            .performScrollToIndex(2).performClick()

        composeTestRule.onNode(hasTestTag(MainPage.detailScreen), useUnmergedTree = true)
            .assertIsDisplayed()

    }
}