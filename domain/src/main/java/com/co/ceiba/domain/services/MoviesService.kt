package com.co.ceiba.domain.services

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieProxy
import kotlinx.coroutines.flow.Flow

class MoviesService (private val movieProxy: MovieProxy) {
     suspend operator fun invoke(): Flow<List<Movie>> = movieProxy.getMovies()
}