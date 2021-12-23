package com.co.ceiba.infrastructure.movie.anticorruption

import com.co.ceiba.domain.models.Movie
import com.co.ceiba.infrastructure.movie.httpclient.dto.MovieDto
import com.co.ceiba.infrastructure.movie.httpclient.dto.MoviesDto
import com.co.ceiba.infrastructure.movie.persistence.entity.MovieEntity
import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies

class MovieTranslate {
    companion object{
        fun  mapMovieEntityToDomain (movieEntity: MovieEntity): Movie {
            return Mapper.convert(movieEntity)
        }

        private fun mapMovieDtoToDomain (movieDto: MovieDto): Movie{
           return Mapper.convert(movieDto)
        }

        fun mapMoviesDtoToDomain (moviesDto: MoviesDto): List<Movie> {
            return moviesDto.results.map { mapMovieDtoToDomain(it) }
        }
    }
}

class MapperDto() : ModelMapper() {
    init {
        configuration.matchingStrategy = MatchingStrategies.LOOSE
        configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        configuration.isFieldMatchingEnabled = true
        configuration.isSkipNullEnabled = true
    }
}

object Mapper {
    val mapper = MapperDto()

    inline fun <S, reified T> convert(source: S): T = mapper.map(source, T::class.java)
}