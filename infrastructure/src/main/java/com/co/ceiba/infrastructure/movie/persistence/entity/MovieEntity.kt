package com.co.ceiba.infrastructure.movie.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.co.ceiba.domain.models.Movie

@Entity
data class MovieEntity(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: String,
    @PrimaryKey val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
){
    constructor(movie: Movie): this(
        adult = movie.adult,
        backdropPath = movie.backdropPath,
        genreIds = movie.genreIds,
        id = movie.id,
        originalLanguage = movie.originalLanguage,
        originalTitle = movie.originalTitle,
        overview = movie.overview,
        popularity = movie.popularity,
        posterPath = movie.posterPath,
        releaseDate = movie.releaseDate,
        title = movie.title,
        video = movie.video,
        voteAverage = movie.voteAverage,
        voteCount = movie.voteCount
    )
}