package com.co.ceiba.infrastructure.repositories

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieRepository
import com.co.ceiba.domain.repositories.MovieLocalRepository
import com.co.ceiba.domain.repositories.MoviePreferencesRepository
import com.co.ceiba.domain.repositories.MovieRemoteRepository
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl (private val localRepository: MovieLocalRepository, private val remoteRepository: MovieRemoteRepository, private val preferencesRepository: MoviePreferencesRepository) :
    MovieRepository {

    override fun getLastUpdatedPreference(): String = preferencesRepository.getLastUpdatedPreference()


    override fun saveLastUpdatedPreference(lastUpdated: String) {
        preferencesRepository.saveLastUpdatedPreference(lastUpdated)
    }

    override fun isUpdated(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmptyLocal(): Boolean = localRepository.isEmpty()

    override fun getMoviesLocal(): Flow<List<Movie>> = localRepository.getAllMovies()

    override fun getMoviesRemote(): Flow<List<Movie>> = remoteRepository.getMovies()

    override fun getMovie(id:Int): Flow<Movie>  = localRepository.getMovieById(id)

    override suspend fun saveMovies(movies: List<Movie>){
        localRepository.insertMovies(movies)
    }


}