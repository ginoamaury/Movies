package com.co.ceiba.infrastructure.httpclient

import com.co.ceiba.infrastructure.httpclient.model.MovieDto
import com.co.ceiba.infrastructure.httpclient.model.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val QUERY_MOVIES = "movie/now_playing"
private const val QUERY_MOVIE = "movie/{movie_id}"
private const val QUERY_API_KEY = "api_key"
private const val API_KEY = "14900d7797c47f6c10e4ddbb620a6ce9"
private const val QUERY_MOVIE_ID = "movie_id"
interface IMovieService {

    @GET(QUERY_MOVIES)
    suspend fun getAllMovies(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY
    ) : MoviesDto

    @GET(QUERY_MOVIE)
    suspend fun getMovie(
        @Path(QUERY_MOVIE_ID)id:Int,
        @Query(QUERY_API_KEY) apiKey :String = API_KEY
    ) : MovieDto

}