package com.co.ceiba.infrastructure.movie.repositories

import com.co.ceiba.domain.models.Coordinator
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieRepository
import com.co.ceiba.domain.repositories.MovieLocalRepository
import com.co.ceiba.domain.repositories.MovieTemporalRepository
import com.co.ceiba.domain.repositories.MovieRemoteRepository
import kotlinx.coroutines.flow.*
import java.util.*

class MovieRepositoryImpl (private val localRepository: MovieLocalRepository, private val remoteRepository: MovieRemoteRepository, private val temporalRepository: MovieTemporalRepository) :
    MovieRepository {

    override fun getMovie(id:Int): Flow<Movie>  = localRepository.getMovieById(id)

    override suspend fun getMovies (): Flow<List<Movie>> {
        val sharedTime = temporalRepository.getLastUpdatedPreference()
        val isEmptyLocal = localRepository.isEmpty()
        return if (!isEmptyLocal && Coordinator.isUpdated(sharedTime)){
            localRepository.getAllMovies()
        }else{
            val response =  remoteRepository.getMovies()
            response.collect { movies ->
                saveMovies(movies)
            }
            response
        }
    }

    override suspend fun saveMovies(movies: List<Movie>){
        localRepository.insertMovies(movies)
        temporalRepository.saveLastUpdatedPreference(Calendar.getInstance().timeInMillis.toString())
    }


}