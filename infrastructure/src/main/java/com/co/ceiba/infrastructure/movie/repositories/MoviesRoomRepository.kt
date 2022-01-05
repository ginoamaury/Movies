package com.co.ceiba.infrastructure.movie.repositories

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.MovieLocalRepository
import com.co.ceiba.infrastructure.movie.anticorruption.MovieTranslate
import com.co.ceiba.infrastructure.movie.persistence.dao.MovieDao
import com.co.ceiba.infrastructure.movie.persistence.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRoomRepository(private val movieDao: MovieDao) : MovieLocalRepository {
    override fun isEmpty(): Boolean {
        return getCountMovies() <= 0
    }

    override fun movieExist(id: Int): Boolean {
        return movieDao.movieExist(id) > 0
    }

    override fun getAllMovies(): Flow<List<Movie>> {
        return movieDao.getAllMovies()
            .map { movies -> movies.map { MovieTranslate.mapMovieEntityToDomain(it) } }
    }

    override fun getMovieById(id: Int): Flow<Movie> {
        return movieDao.getMovieById(id)
            .map { movie -> MovieTranslate.mapMovieEntityToDomain(movie) }
    }

    override fun getCountMovies(): Int {
        return movieDao.getCountMovies()
    }

    override suspend fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovie(movies.map { MovieEntity(it) })
    }
}