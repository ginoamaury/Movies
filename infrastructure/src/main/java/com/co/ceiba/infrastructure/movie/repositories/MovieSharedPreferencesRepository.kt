package com.co.ceiba.infrastructure.movie.repositories

import android.content.SharedPreferences
import com.co.ceiba.domain.repositories.MovieTemporalRepository
import com.co.ceiba.infrastructure.dependencyInjection.LAST_UPDATED


class MovieSharedPreferencesRepository(private val sharedPreferences: SharedPreferences) :
    MovieTemporalRepository {

    override fun getLastUpdatedPreference(): String =
        sharedPreferences.getString(LAST_UPDATED, "").toString()

    override fun saveLastUpdatedPreference(lastUpdated: String) {
        sharedPreferences.edit().putString(LAST_UPDATED, lastUpdated).apply()
    }

}