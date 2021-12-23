package com.co.ceiba.infrastructure.dependencyInjection

import com.co.ceiba.domain.repositories.MovieProxy
import com.co.ceiba.domain.services.MovieService
import com.co.ceiba.domain.services.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun getMovies (movieProxy: MovieProxy) : MoviesService = MoviesService(movieProxy)

    @Provides
    fun getMovie (movieProxy: MovieProxy) : MovieService = MovieService(movieProxy)

}