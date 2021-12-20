package com.co.ceiba.infrastructure.movie.anticorruption

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.infrastructure.movie.httpclient.dto.MovieDto
import com.co.ceiba.infrastructure.movie.httpclient.dto.MoviesDto
import com.co.ceiba.infrastructure.movie.persistence.entity.MovieEntity

class MovieTranslate {

    companion object{
        fun  mapMovieEntityToDomain (movieEntity: MovieEntity): Movie {
            return Movie(
                movieEntity.adult,
                movieEntity.backdrop_path,
                movieEntity.genre_ids,
                movieEntity.id,
                movieEntity.original_language,
                movieEntity.original_title,
                movieEntity.overview,
                movieEntity.popularity,
                movieEntity.poster_path,
                movieEntity.release_date,
                movieEntity.title,
                movieEntity.video,
                movieEntity.vote_average,
                movieEntity.vote_count
            )
        }

        fun mapMovieDtoToDomain (movieDto: MovieDto): Movie{
            return Movie(
                movieDto.adult,
                movieDto.backdrop_path,
                movieDto.genre_ids.toString(),
                movieDto.id,
                movieDto.original_language,
                movieDto.original_title,
                movieDto.overview,
                movieDto.popularity,
                movieDto.poster_path,
                movieDto.release_date,
                movieDto.title,
                movieDto.video,
                movieDto.vote_average,
                movieDto.vote_count
            )
        }

        fun mapMoviesDtoToDomain (moviesDto: MoviesDto): List<Movie> {
            return moviesDto.results.map { mapMovieDtoToDomain(it) }
        }
    }


}