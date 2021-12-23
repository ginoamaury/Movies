package com.co.ceiba.domain.services

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

    private val movie = Movie(
        false,
        "",
        "genres",
        413323,
        "en",
        "Deadpool: From Comics to Screen... to Screen",
        "This documentary divided into five segments examines the source and its path to the movies, backstory, special effects story/character areas, cast and performances. It includes notes from Reynolds, Liefeld, Miller, Wernick, Reese, executive producers Aditya Sood and Stan Lee, co-creator/comics writer Fabian Nicieza, producer Simon Kinberg, comics writer Joe Kelly, specialty costume designer Russell Shinkle, makeup designer Bill Corso, production designer Sean Haworth, director of photography Ken Seng, executive producer/unit production manager John J. Kelly, previs supervisor Franck Balson, stunt coordinator Philip J. Silvera, visual effects supervisors Pauline Duvall and Jonathan Rothbart, visual effects producer Annemarie Griggs, 2nd unit director/stunt coordinator Robert Alonzo, special effects coordinator Alex Burdett, utility stunts Regis Harrington, composer Tom Holkenberg, and actors Morena Baccarin, TJ Miller, Brianna Hildebrand, Leslie Uggams, Ed Skrein, and Gina Carano.",
        0.0,
        "/chV0avy5ogIB2PMTInT4KpHDzwj.jpg",
        "2016-05-10",
        "Deadpool: From Comics to Screen... to Screen",
        false,
        0.0,
        0
    )

    private val flowMovie: Flow<Movie> = flow { emit(movie) }

    @Test
    fun movieService_whenIsInvoked_movieResponse(){
        runBlocking {
            //Arrange
            val idMovie = 413323
            Mockito.`when`(movieProxy.getMovie(idMovie)).thenReturn(flowMovie)
            //Act
            val result = movieService.invoke(idMovie).first()
            //Assert
            Mockito.verify(movieProxy,Mockito.times(1)).getMovie(idMovie)
            Assert.assertEquals(movie,result)
        }

    }

}