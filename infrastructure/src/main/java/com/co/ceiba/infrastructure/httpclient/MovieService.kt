package com.co.ceiba.infrastructure.httpclient

import com.co.ceiba.infrastructure.movie.httpclient.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val QUERY_MOVIES = "movie/now_playing"
private const val QUERY_API_KEY = "api_key"
private const val API_KEY = "14900d7797c47f6c10e4ddbb620a6ce9"
interface IMovieService {

    @GET(QUERY_MOVIES)
    suspend fun getAllMovies(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ) : MoviesDto

}