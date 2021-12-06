package com.co.ceiba.infrastructure.persistence.dao

import androidx.room.*
import com.co.ceiba.infrastructure.persistence.dto.MovieDto
import kotlinx.coroutines.flow.Flow

@Dao
interface IMovieDao {

    @Transaction
    @Query("SELECT * FROM MovieDto")
    fun getAllMovies (): Flow<List<MovieDto>>

    @Transaction
    @Query("SELECT * FROM MovieDto WHERE id = :id")
    fun getMovieById(id : Int) : Flow<MovieDto>

    @Transaction
    @Query("SELECT COUNT(*) FROM MovieDto")
    fun getCountMovies() : Int

    @Transaction
    @Query("SELECT COUNT(id) FROM MovieDto WHERE id= :id")
    fun movieExist(id:Int):Int

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieDto>)

}