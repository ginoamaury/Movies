package com.co.ceiba.infrastructure.dependencyInjection

import com.co.ceiba.domain.repositories.IMovieRepository
import com.co.ceiba.domain.services.GetMovie
import com.co.ceiba.domain.services.GetMovies
import com.co.ceiba.domain.services.SaveMovies
import com.co.ceiba.infrastructure.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun getMovies (movieRepository: IMovieRepository) : GetMovies = GetMovies(movieRepository)

    @Provides
    fun getMovie (movieRepository: IMovieRepository) : GetMovie = GetMovie(movieRepository)

    @Provides
    fun saveMovies (movieRepository: IMovieRepository) : SaveMovies = SaveMovies(movieRepository)

}