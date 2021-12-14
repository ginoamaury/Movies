package com.co.ceiba.infrastructure.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.co.ceiba.infrastructure.persistence.dao.MovieDao
import com.co.ceiba.infrastructure.persistence.dto.MovieEntity

@Database(entities = [MovieEntity::class],version = 1, exportSchema = false)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}