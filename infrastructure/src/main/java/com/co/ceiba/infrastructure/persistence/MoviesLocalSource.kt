package com.co.ceiba.infrastructure.persistence

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.sources.ILocalSource
import com.co.ceiba.infrastructure.persistence.dao.IMovieDao
import com.co.ceiba.infrastructure.persistence.dto.MovieDto
import kotlinx.coroutines.flow.*

class MoviesLocalSource (private val movieDao: IMovieDao) : ILocalSource {
    override fun isEmpty(): Boolean {
        return getCountMovies() <=0
    }

    override fun movieExist(id:Int): Boolean {
        return movieDao.movieExist(id) > 0
    }

    override fun getAllMovies(): Flow<List<Movie>> {
        return movieDao.getAllMovies().map { movies -> movies.map { it.map() } }
    }

    override fun getMovieById(id: Int): Flow<Movie> {
        return movieDao.getMovieById(id).map{ movie -> movie.map()}
    }

    override fun getCountMovies(): Int {
        return movieDao.getCountMovies()
    }

    override suspend fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovie(movies.map { MovieDto(it) })
    }
}