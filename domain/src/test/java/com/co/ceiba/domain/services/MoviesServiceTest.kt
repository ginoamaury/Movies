package com.co.ceiba.domain.services

import com.co.ceiba.domain.builder.Builder
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieProxy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesServiceTest {

    @Mock
    lateinit var movieProxy : MovieProxy

    @InjectMocks
    lateinit var moviesService: MoviesService

    private val flowMovie: Flow<List<Movie>> = Builder.getFlowListMovie()

    @Test
    fun moviesService_whenIsInvoked_moviesResponse(){
        runBlocking {
            //Arrange
            Mockito.`when`(movieProxy.getMovies()).thenReturn(flowMovie)
            //Act
            val result = moviesService.invoke()
            //Assert
            Mockito.verify(movieProxy, Mockito.times(1)).getMovies()
            Assert.assertEquals(flowMovie,result)
        }

    }

}