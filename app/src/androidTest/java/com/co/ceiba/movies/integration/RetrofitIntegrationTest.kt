package com.co.ceiba.movies.integration

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.co.ceiba.infrastructure.httpclient.MovieService
import com.co.ceiba.infrastructure.movie.repositories.MoviesRetrofitRepository
import junit.framework.Assert
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(AndroidJUnit4::class)
class RetrofitIntegrationTest {

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

    @Test
    fun testA() {
        runBlocking {
           val response = moviesRetrofitRepository.getMovies().first()
            println(response[0].title)
            Assert.assertNotNull(response)
        }

    }


}