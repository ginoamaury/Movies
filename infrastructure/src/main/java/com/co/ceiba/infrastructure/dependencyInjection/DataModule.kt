package com.co.ceiba.infrastructure.dependencyInjection

import com.co.ceiba.domain.repositories.IMovieRepository
import com.co.ceiba.domain.sources.ILocalSource
import com.co.ceiba.domain.sources.IRemoteSource
import com.co.ceiba.infrastructure.httpclient.IMovieService
import com.co.ceiba.infrastructure.httpclient.MoviesRemoteSource
import com.co.ceiba.infrastructure.persistence.MoviesLocalSource
import com.co.ceiba.infrastructure.persistence.dao.IMovieDao
import com.co.ceiba.infrastructure.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesRepository(localSource: ILocalSource, remoteSource: IRemoteSource): IMovieRepository = MovieRepository(localSource,remoteSource)

    @Provides
    fun providesLocalSource (movieDao: IMovieDao): ILocalSource = MoviesLocalSource(movieDao)

    @Provides
    fun providesRemoteSource (moviesService: IMovieService): IRemoteSource = MoviesRemoteSource(moviesService)

}