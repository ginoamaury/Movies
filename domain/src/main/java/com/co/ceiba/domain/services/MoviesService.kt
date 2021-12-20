package com.co.ceiba.domain.services

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class MoviesService (private val movieRepository: MovieRepository) {
     suspend operator fun invoke(): Flow<List<Movie>> = movieRepository.getMovies()
}