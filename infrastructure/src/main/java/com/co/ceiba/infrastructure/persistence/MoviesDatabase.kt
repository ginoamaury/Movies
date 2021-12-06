package com.co.ceiba.infrastructure.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.co.ceiba.infrastructure.persistence.dao.IMovieDao
import com.co.ceiba.infrastructure.persistence.dto.MovieDto

@Database(entities = [MovieDto::class],version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun movieDao(): IMovieDao
}