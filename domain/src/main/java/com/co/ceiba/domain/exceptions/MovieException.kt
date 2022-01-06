package com.co.ceiba.domain.exceptions

import java.lang.Exception

private const val NO_DATA_MOVIE_EXCEPTION_MESSAGE = "We haven't movies to show"

abstract class MovieException (message: String) : Exception(message)
class NoDataMovieException(message: String = NO_DATA_MOVIE_EXCEPTION_MESSAGE) : MovieException(message)
