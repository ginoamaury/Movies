package com.co.ceiba.infrastructure.repositories

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieRemoteRepository
import com.co.ceiba.infrastructure.anticorruption.MovieTranslate
import com.co.ceiba.infrastructure.httpclient.IMovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRemoteRepositoryImpl (private val movieService: IMovieService) : MovieRemoteRepository {
    override fun getMovies(): Flow<List<Movie>> {
        return flow { emit( MovieTranslate.mapMoviesDtoToDomain(movieService.getAllMovies()))}
    }

    override fun getMovie(id: Int): Flow<Movie> {
       return flow { emit( MovieTranslate.mapMovieDtoToDomain(movieService.getMovie(id)))}
    }
}