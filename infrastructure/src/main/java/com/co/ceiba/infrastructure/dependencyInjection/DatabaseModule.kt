package com.co.ceiba.infrastructure.dependencyInjection

import android.app.Application
import androidx.room.Room
import com.co.ceiba.infrastructure.persistence.MoviesDatabase
import com.co.ceiba.infrastructure.movie.persistence.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application): MoviesDatabase = Room.databaseBuilder(
        app,
        MoviesDatabase::class.java,
        "movies-db"
    ).build()

    @Provides
    fun providesMovieDao (database: MoviesDatabase) : MovieDao = database.movieDao()


}