package com.co.ceiba.infrastructure.movie.repositories

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieRemoteRepository
import com.co.ceiba.infrastructure.movie.anticorruption.MovieTranslate
import com.co.ceiba.infrastructure.httpclient.IMovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRetrofitRepository (private val movieService: IMovieService) : MovieRemoteRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return flow { emit( MovieTranslate.mapMoviesDtoToDomain(movieService.getAllMovies()))}
    }
}