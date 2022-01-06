package com.co.ceiba.domain.exceptions

import java.lang.Exception

abstract class MovieException : Exception()
class NoDataMovieException : MovieException()
