package com.co.ceiba.infrastructure.persistence.dao

import androidx.room.*
import com.co.ceiba.infrastructure.persistence.dto.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Transaction
    @Query("SELECT * FROM MovieEntity")
    fun getAllMovies (): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun getMovieById(id : Int) : Flow<MovieEntity>

    @Transaction
    @Query("SELECT COUNT(*) FROM MovieEntity")
    fun getCountMovies() : Int

    @Transaction
    @Query("SELECT COUNT(id) FROM MovieEntity WHERE id= :id")
    fun movieExist(id:Int):Int

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

}