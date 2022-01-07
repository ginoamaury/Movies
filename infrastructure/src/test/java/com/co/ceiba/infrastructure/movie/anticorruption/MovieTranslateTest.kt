package com.co.ceiba.infrastructure.movie.anticorruption

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.infrastructure.movie.httpclient.dto.DatesDto
import com.co.ceiba.infrastructure.movie.httpclient.dto.MovieDto
import com.co.ceiba.infrastructure.movie.httpclient.dto.MoviesDto
import com.co.ceiba.infrastructure.movie.persistence.entity.MovieEntity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieTranslateTest {

    private val movieTranslate = MovieTranslate

    private val movieEntity = MovieEntity(
        false,
        "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
        "genres",
        123456,
        "en",
        "Spider-Man: No Way Home",
        "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
        0.0,
        "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        "2021-12-15",
        "Spider-Man: No Way Home",
        false,
        0.0,
        0
    )

    private val movieDto = MovieDto(
        false,
        "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
        emptyList(),
        123456,
        "en",
        "Spider-Man: No Way Home",
        "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
        0.0,
        "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        "2021-12-15",
        "Spider-Man: No Way Home",
        false,
        0.0,
        0
    )

    private val moviesList= listOf(movieDto)

    private val moviesDto = MoviesDto(
        DatesDto("",""),
        0,
        moviesList,
        1,
1
    )

    @Test
    fun mapMovie_WhenEntityToDomain_MovieReturn(){
        //Arrange
        val movie = movieEntity
        //Act
        val result = movieTranslate.mapMovieEntityToDomain(movie)
        //Assert
        Assert.assertTrue(result.javaClass == Movie::class.java)
    }

    @Test
    fun mapMovie_WhenMovieDtoToDomain_MovieReturn(){
        //Arrange
        val movie = moviesDto
        //Act
        val result = movieTranslate.mapMoviesDtoToDomain(movie)[0]
        //Assert
        Assert.assertTrue(result.javaClass == Movie::class.java)
    }

}