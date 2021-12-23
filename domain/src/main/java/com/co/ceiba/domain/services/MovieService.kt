package com.co.ceiba.domain.services

import com.co.ceiba.domain.repositories.MovieProxy

class MovieService (private val movieProxy: MovieProxy) {
    operator fun invoke (id:Int) = movieProxy.getMovie(id)
}