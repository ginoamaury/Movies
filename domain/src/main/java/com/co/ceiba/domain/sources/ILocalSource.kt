package com.co.ceiba.domain.sources

import com.co.ceiba.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface ILocalSource {
    fun isEmpty (): Boolean
    fun movieExist(id: Int): Boolean
    fun getAllMovies () : Flow<List<Movie>>
    fun getMovieById (id:Int) : Flow<Movie>
    fun getCountMovies (): Int
    suspend fun insertMovies (movies : List<Movie>)
}