package com.co.ceiba.movies.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.co.ceiba.movies.MainPage
import com.co.ceiba.movies.Navigation
import com.co.ceiba.movies.ui.theme.MoviesTheme
import com.co.ceiba.movies.ui.view.MovieListPreview
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PreviewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkPreview_whenList_isNotDisplayed(){
        //Arrange
        //Act
        composeTestRule.setContent {
            MoviesTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MovieListPreview()
                }
            }
        }
        //Assert
        composeTestRule.onNodeWithTag(MainPage.previewList).assertIsDisplayed()
    }
}