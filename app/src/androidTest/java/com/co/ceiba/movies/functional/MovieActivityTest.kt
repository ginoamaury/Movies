package com.co.ceiba.movies.functional


import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.co.ceiba.movies.MovieActivity
import com.co.ceiba.movies.MainPage
import com.co.ceiba.movies.Navigation
import com.co.ceiba.movies.ui.theme.MoviesTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import kotlin.concurrent.schedule

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MovieActivity>(MovieActivity::class.java)

    @ExperimentalTestApi
    @Test
    fun getAllMovies_whenAllIsRight_okResult() {
        //Arrange
        val delay = 1000L
        val showSplashScreen = false
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation({}, showSplashScreen)
                }
            }
        }
        AsyncTimer.start (delay)
        composeTestRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
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
        val delay = 1000L
        val showSplashScreen = false
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation({}, showSplashScreen)
                }
            }
        }
        AsyncTimer.start (delay)
        composeTestRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
        //Act
        val list = composeTestRule.onNode(hasTestTag(MainPage.movieList), useUnmergedTree = true)
        //Assert
        list.assertIsDisplayed()
            .performScrollToIndex(2).performClick()

        AsyncTimer.start (delay)
        composeTestRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )

        composeTestRule.onNode(hasTestTag(MainPage.detailScreen), useUnmergedTree = true)
            .assertIsDisplayed()

    }
    object AsyncTimer {
        var expired = false
        fun start(delay: Long = 1000){
            expired = false
            Timer().schedule(delay) {
                expired = true
            }
        }
    }


}