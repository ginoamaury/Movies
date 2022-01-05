package com.co.ceiba.movies.integration

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.co.ceiba.domain.repositories.MovieLocalRepository
import com.co.ceiba.domain.repositories.MovieTemporalRepository
import com.co.ceiba.infrastructure.dependencyInjection.LAST_UPDATED
import com.co.ceiba.infrastructure.httpclient.MovieService
import com.co.ceiba.infrastructure.movie.persistence.dao.MovieDao
import com.co.ceiba.infrastructure.movie.repositories.MovieCachedProxy
import com.co.ceiba.infrastructure.movie.repositories.MovieSharedPreferencesRepository
import com.co.ceiba.infrastructure.movie.repositories.MoviesRetrofitRepository
import com.co.ceiba.infrastructure.movie.repositories.MoviesRoomRepository
import com.co.ceiba.infrastructure.persistence.MoviesDatabase
import junit.framework.Assert
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class ProxyIntegrationTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().context

    private val BASEURL = "https://api.themoviedb.org/3/"

    private val client = OkHttpClient().newBuilder()
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(BASEURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieService::class.java)


    private val moviesRetrofitRepository = MoviesRetrofitRepository(api)

    private var database: MoviesDatabase =  Room
        .inMemoryDatabaseBuilder(context, MoviesDatabase::class.java)
        .setTransactionExecutor(Executors.newSingleThreadExecutor())
        .build()

    private var movieDao: MovieDao = database.movieDao()

    private var movieLocalRepository: MovieLocalRepository = MoviesRoomRepository(movieDao = movieDao)

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(LAST_UPDATED, Context.MODE_PRIVATE)

    private val movieTemporalRepository: MovieTemporalRepository = MovieSharedPreferencesRepository(sharedPreferences = sharedPreferences)

    private val movieCachedProxy: MovieCachedProxy = MovieCachedProxy(movieLocalRepository,moviesRetrofitRepository,movieTemporalRepository)

    @Test
    fun getAllMovies_whenIsIntegrationTest_resultOk (){
        runBlocking {
            val response = movieCachedProxy.getMovies().first()
            Assert.assertNotNull(response)
        }
    }

}