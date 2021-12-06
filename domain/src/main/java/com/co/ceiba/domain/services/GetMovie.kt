package com.co.ceiba.domain.services

import com.co.ceiba.domain.repositories.IMovieRepository

class GetMovie (private val movieRepository: IMovieRepository) {
    operator fun invoke (id:Int) = movieRepository.getMovie(id)
}