package com.co.ceiba.infrastructure.persistence.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.co.ceiba.domain.models.Movie

@Entity
data class MovieDto(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: String,
    @PrimaryKey val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
){

    constructor(movie: Movie): this(
        adult = movie.adult,
        backdrop_path = movie.backdrop_path,
        genre_ids = movie.genre_ids,
        id = movie.id,
        original_language = movie.original_language,
        original_title = movie.original_title,
        overview = movie.overview,
        popularity = movie.popularity,
        poster_path = movie.poster_path,
        release_date = movie.release_date,
        title = movie.title,
        video = movie.video,
        vote_average = movie.vote_average,
        vote_count = movie.vote_count
    )

    fun map(): Movie {
        return Movie(
            adult,
            backdrop_path,
            genre_ids,
            id,
            original_language,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count
        )
    }

}