package com.co.ceiba.domain.repositories

import com.co.ceiba.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovies () : Flow<List<Movie>>
    fun getMovie(id:Int): Flow<Movie>
    suspend fun saveMovies (movies: List<Movie>)
}