package com.co.ceiba.movies.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.co.ceiba.movies.MainPage
import com.co.ceiba.movies.ui.theme.MoviesTheme
import com.co.ceiba.movies.ui.view.MovieListPreview
import com.co.ceiba.movies.ui.view.NoDataScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoDataScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkNoDataScreen_whenHasNotData_isDisplayed() {
        //Arrange
        //Act
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NoDataScreen()
                }
            }
        }
        //Assert
        composeTestRule.onAllNodesWithTag(MainPage.noDataScreen)
    }
}