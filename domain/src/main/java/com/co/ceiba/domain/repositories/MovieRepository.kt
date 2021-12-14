package com.co.ceiba.domain.repositories

import com.co.ceiba.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getLastUpdatedPreference (): String
    fun saveLastUpdatedPreference (lastUpdated: String)
    fun isUpdated (): Boolean
    fun isEmptyLocal(): Boolean
    fun getMoviesLocal () : Flow<List<Movie>>
    fun getMoviesRemote () : Flow<List<Movie>>
    fun getMovie(id:Int): Flow<Movie>
    suspend fun saveMovies (movies: List<Movie>)
}