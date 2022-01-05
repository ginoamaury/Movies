package com.co.ceiba.movies.integration

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.co.ceiba.domain.builder.Builder
import com.co.ceiba.infrastructure.movie.persistence.dao.MovieDao
import com.co.ceiba.infrastructure.movie.persistence.entity.MovieEntity
import com.co.ceiba.infrastructure.persistence.MoviesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class RoomIntegrationTest {
    private lateinit var database: MoviesDatabase
    private lateinit var movieDao: MovieDao

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private val context: Context = InstrumentationRegistry.getInstrumentation().context

    private val moviesExpected = Builder.getListMovie().map {  MovieEntity(it) }


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        database = Room
            .inMemoryDatabaseBuilder(context, MoviesDatabase::class.java)
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()
        movieDao = database.movieDao()
    }

    @ExperimentalCoroutinesApi
    @After
    fun cleanup() {
        database.close()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testInsertAndGetAllCores() {
        runBlocking {
            movieDao.insertMovie(moviesExpected)
            val result = movieDao.getAllMovies().first()
            Assert.assertNotNull(result)
        }
    }
}