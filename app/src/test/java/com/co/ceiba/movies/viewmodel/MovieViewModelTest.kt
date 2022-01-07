package com.co.ceiba.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.co.ceiba.domain.builder.Builder
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.services.MovieService
import com.co.ceiba.movies.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config


@RunWith(MockitoJUnitRunner::class)
@Config(manifest = Config.NONE)
class MovieViewModelTest {

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get: Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var movieService: MovieService

    private lateinit var movieViewModel: MovieViewModel


    @ExperimentalCoroutinesApi
    @Before
    fun start(){
        movieViewModel = MovieViewModel(movieService = movieService,coroutineTestRule.testDispatcher)
    }


    private val flowMovie: Flow<Movie> = Builder.getFlowMovie()


    @Test
    fun getMovie_whenGetMovie_okResponse() {
        runBlocking {
            //Arrange
            val idMovie = 123456
            Mockito.`when`(movieService.invoke(idMovie)).thenReturn(flowMovie)
            //Act
            movieViewModel.getMovie(idMovie)
            //Assert
            Mockito.verify(movieService, Mockito.times(1)).invoke(idMovie)
        }
    }

    @Test
    fun getMovie_whenGetMovie_exceptionResponse(){
        runBlocking {
            //Arrange
            val idMovie = 123456
            Mockito.`when`(movieService.invoke(idMovie)).thenReturn(null)
            //Act
            movieViewModel.getMovie(idMovie)
            //Assert
            Assert.assertTrue(movieViewModel.uiState.value.error)
        }
    }

}