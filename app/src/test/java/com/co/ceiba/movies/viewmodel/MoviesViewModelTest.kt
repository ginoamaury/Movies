package com.co.ceiba.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.co.ceiba.domain.builder.Builder
import com.co.ceiba.domain.services.MoviesService
import com.co.ceiba.movies.CoroutineTestRule
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
class MoviesViewModelTest {

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var moviesService: MoviesService

    private lateinit var moviesViewModel: MoviesViewModel


    @Before
    fun start(){
        moviesViewModel = MoviesViewModel(moviesService = moviesService,coroutineTestRule.testDispatcher)
    }

    @Test
    fun getMovies_whenMoviesServicesWasCalled_okResponse() {
        runBlocking {
            Mockito.verify(moviesService,Mockito.times(1)).invoke()
        }
    }

    @Test
    fun getMovies_whenGetMovies_exceptionResponse(){
        runBlocking {
            //Arrange
            Mockito.`when`(moviesService.invoke()).thenReturn(null)
            //Act
            moviesViewModel.getMovies()
            //Assert
            Assert.assertTrue(moviesViewModel.uiState.value.error)
        }
    }

    @Test
    fun getMovies_whenGetMovies_okResponse(){
        runBlocking {
            //Arrange
            Mockito.`when`(moviesService.invoke()).thenReturn(Builder.getFlowListMovie())
            //Act
            moviesViewModel.getMovies()
            //Assert
            Assert.assertEquals(moviesViewModel.uiState.value.success[0].title, Builder.getMovie().title)
        }
    }

}