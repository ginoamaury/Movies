package com.co.ceiba.infrastructure.dependencyInjection

import android.content.SharedPreferences
import com.co.ceiba.domain.repositories.MovieRepository
import com.co.ceiba.domain.repositories.MovieLocalRepository
import com.co.ceiba.domain.repositories.MoviePreferencesRepository
import com.co.ceiba.domain.repositories.MovieRemoteRepository
import com.co.ceiba.infrastructure.httpclient.IMovieService
import com.co.ceiba.infrastructure.repositories.MoviesRemoteRepositoryImpl
import com.co.ceiba.infrastructure.repositories.MoviesLocalRepositoryImpl
import com.co.ceiba.infrastructure.persistence.dao.MovieDao
import com.co.ceiba.infrastructure.repositories.MoviePreferencesRepositoryImpl
import com.co.ceiba.infrastructure.repositories.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesRepository(
        localRepository: MovieLocalRepository,
        remoteRepository: MovieRemoteRepository,
        preferencesRepository: MoviePreferencesRepository
    ): MovieRepository =
        MovieRepositoryImpl(localRepository, remoteRepository, preferencesRepository)

    @Provides
    fun providesLocalSource(movieDao: MovieDao): MovieLocalRepository =
        MoviesLocalRepositoryImpl(movieDao)

    @Provides
    fun providesRemoteSource(moviesService: IMovieService): MovieRemoteRepository =
        MoviesRemoteRepositoryImpl(moviesService)

    @Provides
    fun providesPreferencesRepository(sharedPreferences: SharedPreferences): MoviePreferencesRepository = MoviePreferencesRepositoryImpl(sharedPreferences)

}