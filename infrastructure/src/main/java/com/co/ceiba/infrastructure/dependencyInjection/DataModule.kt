package com.co.ceiba.infrastructure.dependencyInjection

import android.content.SharedPreferences
import com.co.ceiba.domain.repositories.MovieProxy
import com.co.ceiba.domain.repositories.MovieLocalRepository
import com.co.ceiba.domain.repositories.MovieTemporalRepository
import com.co.ceiba.domain.repositories.MovieRemoteRepository
import com.co.ceiba.infrastructure.httpclient.MovieService
import com.co.ceiba.infrastructure.movie.persistence.dao.MovieDao
import com.co.ceiba.infrastructure.movie.repositories.*
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
    ): MovieProxy =
        MovieCachedProxy(localRepository, remoteRepository, temporalRepository)

    @Provides
    fun providesLocalSource(movieDao: MovieDao): MovieLocalRepository =
        MoviesRoomRepository(movieDao)

    @Provides
    fun providesRemoteSource(moviesService: MovieService): MovieRemoteRepository =
        MoviesRetrofitRepository(moviesService)

    @Provides
    fun providesPreferencesRepository(sharedPreferences: SharedPreferences): MovieTemporalRepository = MovieSharedPreferencesRepository(sharedPreferences)

}