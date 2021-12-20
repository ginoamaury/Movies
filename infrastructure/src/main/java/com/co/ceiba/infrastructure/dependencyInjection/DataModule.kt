package com.co.ceiba.infrastructure.dependencyInjection

import android.app.Application
import android.content.SharedPreferences
import com.co.ceiba.domain.repositories.MovieRepository
import com.co.ceiba.domain.repositories.MovieLocalRepository
import com.co.ceiba.domain.repositories.MovieTemporalRepository
import com.co.ceiba.domain.repositories.MovieRemoteRepository
import com.co.ceiba.infrastructure.httpclient.IMovieService
import com.co.ceiba.infrastructure.movie.persistence.dao.MovieDao
import com.co.ceiba.infrastructure.movie.repositories.MoviesRetrofitRepository
import com.co.ceiba.infrastructure.movie.repositories.MoviesRoomRepository
import com.co.ceiba.infrastructure.movie.repositories.MovieSharedPreferencesRepository
import com.co.ceiba.infrastructure.movie.repositories.MovieRepositoryImpl
import dagger.Binds
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
        temporalRepository: MovieTemporalRepository
    ): MovieRepository =
        MovieRepositoryImpl(localRepository, remoteRepository, temporalRepository)

    @Provides
    fun providesLocalSource(movieDao: MovieDao): MovieLocalRepository =
        MoviesRoomRepository(movieDao)

    @Provides
    fun providesRemoteSource(moviesService: IMovieService): MovieRemoteRepository =
        MoviesRetrofitRepository(moviesService)

    @Provides
    fun providesPreferencesRepository(sharedPreferences: SharedPreferences): MovieTemporalRepository = MovieSharedPreferencesRepository(sharedPreferences)

}