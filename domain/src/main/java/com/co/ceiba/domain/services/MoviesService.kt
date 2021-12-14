package com.co.ceiba.domain.services

import com.co.ceiba.domain.models.Coordinator
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.util.*

class MoviesService (private val movieRepository: MovieRepository) {

   suspend operator fun invoke(): Flow<List<Movie>> {
        val sharedTime = movieRepository.getLastUpdatedPreference()
        val isEmptyLocal = movieRepository.isEmptyLocal()
        return if (!isEmptyLocal && Coordinator.isUpdated(sharedTime)){
            movieRepository.getMoviesLocal()
        }else{
            val response =  movieRepository.getMoviesRemote()
            response.collect { movies ->
                saveMovies(movies)
            }
            response
        }
    }

    private suspend fun saveMovies (movies: List<Movie>){
        movieRepository.saveMovies(movies)
        movieRepository.saveLastUpdatedPreference(Calendar.getInstance().timeInMillis.toString())
    }

}