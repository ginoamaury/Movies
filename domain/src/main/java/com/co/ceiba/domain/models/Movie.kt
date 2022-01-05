package com.co.ceiba.domain.models

import com.co.ceiba.domain.anotations.NoArg

@NoArg
class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: String,
    val id: Int,
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
)