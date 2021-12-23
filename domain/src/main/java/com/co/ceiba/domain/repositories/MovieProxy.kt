package com.co.ceiba.domain.repositories

import com.co.ceiba.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieProxy {
    fun getMovie(id:Int): Flow<Movie>
    suspend fun getMovies(): Flow<List<Movie>>
}