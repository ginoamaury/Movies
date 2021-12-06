package com.co.ceiba.domain.services

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.IMovieRepository

class SaveMovies (private val movieRepository: IMovieRepository) {
    suspend operator fun invoke(movies: List<Movie>) = movieRepository.saveMovies(movies)
}