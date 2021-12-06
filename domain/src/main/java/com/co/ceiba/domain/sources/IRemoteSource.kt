package com.co.ceiba.domain.sources

import com.co.ceiba.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface IRemoteSource {
     fun getMovies(): Flow<List<Movie>>
     fun getMovie(id:Int) : Flow<Movie>
}