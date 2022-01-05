package com.co.ceiba.infrastructure.movie.repositories

import com.co.ceiba.domain.exceptions.NoDataMovie
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieRemoteRepository
import com.co.ceiba.infrastructure.httpclient.MovieService
import com.co.ceiba.infrastructure.movie.anticorruption.MovieTranslate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MoviesRetrofitRepository(private val movieService: MovieService) : MovieRemoteRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return flow { emit(movieService.getAllMovies()) }.catch {
            throw NoDataMovie()
        }.map {
            MovieTranslate.mapMoviesDtoToDomain(it)
        }
    }
}