package com.co.ceiba.infrastructure.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.co.ceiba.infrastructure.movie.persistence.dao.MovieDao
import com.co.ceiba.infrastructure.movie.persistence.entity.MovieEntity

@Database(entities = [MovieEntity::class],version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}