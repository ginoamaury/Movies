package com.co.ceiba.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.co.ceiba.domain.builder.Builder
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.services.MoviesService
import com.co.ceiba.movies.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
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

    @ExperimentalCoroutinesApi
    @get: Rule
    var coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var moviesService: MoviesService

    private lateinit var moviesViewModel: MoviesViewModel


    @ExperimentalCoroutinesApi
    @Before
    fun start(){
        moviesViewModel = MoviesViewModel(moviesService = moviesService,coroutineTestRule.testDispatcher)
    }

    @Test
    fun getMovies_whenGetMovies_okResponse() {
        runBlocking {
            Mockito.verify(moviesService,Mockito.times(1)).invoke()
        }
    }


}