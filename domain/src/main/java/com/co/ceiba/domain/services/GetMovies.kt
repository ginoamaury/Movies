package com.co.ceiba.domain.services

import com.co.ceiba.domain.repositories.IMovieRepository

class GetMovies (private val movieRepository: IMovieRepository) {
    operator fun invoke() = movieRepository.getMovies()
}