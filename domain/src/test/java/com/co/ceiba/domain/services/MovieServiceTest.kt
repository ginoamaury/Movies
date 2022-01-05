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
class MovieServiceTest {

    @Mock
    lateinit var movieProxy : MovieProxy

    @InjectMocks
    lateinit var movieService: MovieService

    private val movie = Builder.getMovie()

    private val flowMovie: Flow<Movie> = Builder.getFlowMovie()

    @Test
    fun movieService_whenIsInvoked_movieResponse(){
        runBlocking {
            //Arrange
            val idMovie = movie.id
            Mockito.`when`(movieProxy.getMovie(idMovie)).thenReturn(flowMovie)
            //Act
            val result = movieService.invoke(idMovie)
            //Assert
            Mockito.verify(movieProxy,Mockito.times(1)).getMovie(idMovie)
            Assert.assertEquals(flowMovie,result)
        }

    }

}