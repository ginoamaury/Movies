package com.co.ceiba.movies

import android.app.Application
import com.co.ceiba.infrastructure.persistence.MoviesDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApp :Application()