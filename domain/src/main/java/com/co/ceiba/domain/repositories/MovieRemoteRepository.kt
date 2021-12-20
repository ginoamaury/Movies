package com.co.ceiba.domain.repositories

import com.co.ceiba.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRemoteRepository {
     fun getMovies(): Flow<List<Movie>>
}