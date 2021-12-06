package com.co.ceiba.infrastructure.repositories

import android.util.Log
import com.co.ceiba.domain.models.Movie
import com.co.ceiba.domain.repositories.IMovieRepository
import com.co.ceiba.domain.sources.ILocalSource
import com.co.ceiba.domain.sources.IRemoteSource
import com.co.ceiba.infrastructure.persistence.dto.MovieDto
import kotlinx.coroutines.flow.*

class MovieRepository (private val localSource: ILocalSource, private val remoteSource: IRemoteSource) : IMovieRepository {

    override fun getMovies(): Flow<List<Movie>> {
        val isEmpty = localSource.isEmpty()
        return if (isEmpty){
            remoteSource.getMovies()
        }else{
            val a = localSource.getAllMovies()
            a
        }
    }

    override fun getMovie(id:Int): Flow<Movie> {
        val movieExist = localSource.movieExist(id)
        return if (movieExist){
            val movie = localSource.getMovieById(id)
            movie
        }else{
            remoteSource.getMovie(id)
        }
    }

    override suspend fun saveMovies(movies: List<Movie>){
        localSource.insertMovies(movies)
    }


}