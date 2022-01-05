package com.co.ceiba.movies.movie

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.co.ceiba.domain.builder.Builder
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.movies.MainPage
import com.co.ceiba.movies.ui.movie.MovieAboutSection
import com.co.ceiba.movies.ui.theme.MoviesTheme
import com.co.ceiba.movies.ui.view.MovieDetailScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutMovieSectionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val movie = Builder.getMovie()

    @Test
    fun showAboutSection_whenIsLoaded_isDisplayed(){
        //Arrange
        //Act
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MovieAboutSection(movie)
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithText(MainPage.resumeSection).assertIsDisplayed()
    }

}