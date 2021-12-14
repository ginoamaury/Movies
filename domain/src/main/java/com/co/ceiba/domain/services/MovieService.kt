package com.co.ceiba.domain.services

import com.co.ceiba.domain.repositories.MovieRepository

class MovieService (private val movieRepository: MovieRepository) {
    operator fun invoke (id:Int) = movieRepository.getMovie(id)
}