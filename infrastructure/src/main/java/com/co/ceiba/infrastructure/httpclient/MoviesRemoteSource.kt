package com.co.ceiba.infrastructure.httpclient

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.sources.IRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRemoteSource (private val movieService: IMovieService) : IRemoteSource{
    override fun getMovies(): Flow<List<Movie>> {
        return flow { emit(movieService.getAllMovies().map()) }
    }

    override fun getMovie(id: Int): Flow<Movie> {
       return flow { emit(movieService.getMovie(id).map())}
    }
}