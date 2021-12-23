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
class MoviesServiceTest {

    @Mock
    lateinit var movieProxy : MovieProxy

    @InjectMocks
    lateinit var moviesService: MoviesService

    private val movieDeadpool = Movie(
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

    private val movieSpiderman = Movie(
        false,
        "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
        "genres",
        123456,
        "en",
        "Spider-Man: No Way Hom",
        "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
        0.0,
        "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        "2021-12-15",
        "Spider-Man: No Way Home",
        false,
        0.0,
        0
    )

    private val movies = listOf(movieDeadpool,movieSpiderman)
    private val flowMovie: Flow<List<Movie>> = flow { emit(movies) }

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