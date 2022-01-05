package com.co.ceiba.infrastructure.movie.repositories

import com.co.ceiba.domain.builder.Builder
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieLocalRepository
import com.co.ceiba.domain.repositories.MovieRemoteRepository
import com.co.ceiba.domain.repositories.MovieTemporalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MovieCachedProxyTest {

    @Mock
    private lateinit var localRepository: MovieLocalRepository

    @Mock
    private lateinit var remoteRepository: MovieRemoteRepository

    @Mock
    private lateinit var temporalRepository: MovieTemporalRepository

    @InjectMocks
    private lateinit var movieCachedProxy: MovieCachedProxy

    private val movies = Builder.getListMovie()
    private val flowMovies: Flow<List<Movie>> = flow { emit(movies) }
    private val flowMovie: Flow<Movie> = flow { emit(movies[0]) }

    @Test
    fun getMovie_whenIsLocalRepository_movieResult() {
        runBlocking {
            //Arrange
            val idMovie = movies[0].id
            Mockito.`when`(localRepository.getMovieById(idMovie)).thenReturn(flowMovie)
            //Act
            val result = movieCachedProxy.getMovie(idMovie)
            //Assert
            Mockito.verify(localRepository, Mockito.times(1)).getMovieById(idMovie)
            Assert.assertEquals(flowMovie, result)
        }
    }

    @Test
    fun getMovies_whenLocalRepositoryIsEmpty_moviesResultFromRemote() {
        runBlocking {
            //Arrange
            val updateMills = Calendar.getInstance().timeInMillis.toString()
            Mockito.`when`(temporalRepository.getLastUpdatedPreference()).thenReturn(updateMills)
            Mockito.`when`(localRepository.isEmpty()).thenReturn(true)
            Mockito.`when`(remoteRepository.getMovies()).thenReturn(flowMovies)
            //Act
            val result = movieCachedProxy.getMovies()
            //Assert
            Mockito.verify(temporalRepository, Mockito.times(1)).getLastUpdatedPreference()
            Mockito.verify(localRepository, Mockito.times(1)).isEmpty()
            Mockito.verify(localRepository, Mockito.times(0)).getAllMovies()
            Mockito.verify(remoteRepository, Mockito.times(1)).getMovies()
            Mockito.verify(localRepository, Mockito.times(1)).insertMovies(movies)
            Mockito.verify(temporalRepository, Mockito.times(1))
                .saveLastUpdatedPreference(anyString())
            Assert.assertEquals(flowMovies, result)
        }
    }

    @Test
    fun getMovies_whenLocalRepositoryIsNotEmptyAndIsUpdated_moviesResultFromLocal() {
        runBlocking {
            //Arrange
            val updateMills = Calendar.getInstance().timeInMillis.toString()
            Mockito.`when`(temporalRepository.getLastUpdatedPreference()).thenReturn(updateMills)
            Mockito.`when`(localRepository.isEmpty()).thenReturn(false)
            Mockito.`when`(localRepository.getAllMovies()).thenReturn(flowMovies)
            //Act
            val result = movieCachedProxy.getMovies()
            //Assert
            Mockito.verify(temporalRepository, Mockito.times(1)).getLastUpdatedPreference()
            Mockito.verify(localRepository, Mockito.times(1)).isEmpty()
            Mockito.verify(localRepository, Mockito.times(1)).getAllMovies()
            Mockito.verify(remoteRepository, Mockito.times(0)).getMovies()
            Assert.assertEquals(flowMovies, result)
        }
    }

    @Test
    fun getMovies_whenLocalRepositoryIsNotEmptyAndIsNotUpdated_moviesResultFromRemote() {
        runBlocking {
            //Arrange
            val actualDate = Calendar.getInstance().timeInMillis
            val updateMills = actualDate - 3700000
            Mockito.`when`(temporalRepository.getLastUpdatedPreference())
                .thenReturn(updateMills.toString())
            Mockito.`when`(localRepository.isEmpty()).thenReturn(false)
            Mockito.`when`(remoteRepository.getMovies()).thenReturn(flowMovies)
            //Act
            val result = movieCachedProxy.getMovies()
            //Assert
            Mockito.verify(temporalRepository, Mockito.times(1)).getLastUpdatedPreference()
            Mockito.verify(localRepository, Mockito.times(1)).isEmpty()
            Mockito.verify(localRepository, Mockito.times(0)).getAllMovies()
            Mockito.verify(remoteRepository, Mockito.times(1)).getMovies()
            Mockito.verify(localRepository, Mockito.times(1)).insertMovies(movies)
            Mockito.verify(temporalRepository, Mockito.times(1))
                .saveLastUpdatedPreference(anyString())
            Assert.assertEquals(flowMovies, result)
        }
    }
}