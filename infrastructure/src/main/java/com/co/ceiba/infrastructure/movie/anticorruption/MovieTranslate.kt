package com.co.ceiba.infrastructure.movie.anticorruption

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.infrastructure.movie.httpclient.dto.MovieDto
import com.co.ceiba.infrastructure.movie.httpclient.dto.MoviesDto
import com.co.ceiba.infrastructure.movie.persistence.entity.MovieEntity

class MovieTranslate {
    companion object {
        fun mapMovieEntityToDomain(movieEntity: MovieEntity): Movie {
            return Mapper.convert(movieEntity)
        }

        private fun mapMovieDtoToDomain(movieDto: MovieDto): Movie {
            return Mapper.convert(movieDto)
        }

        fun mapMoviesDtoToDomain(moviesDto: MoviesDto): List<Movie> {
            return moviesDto.results.map { mapMovieDtoToDomain(it) }
        }
    }
}

