package com.co.ceiba.infrastructure.repositories

import android.content.SharedPreferences
import com.co.ceiba.domain.repositories.MoviePreferencesRepository
import com.co.ceiba.infrastructure.dependencyInjection.LAST_UPDATED


class MoviePreferencesRepositoryImpl(private val sharedPreferences: SharedPreferences): MoviePreferencesRepository{

    override fun getLastUpdatedPreference(): String = sharedPreferences.getString(LAST_UPDATED, "").toString()

    override fun saveLastUpdatedPreference(lastUpdated: String) {
        sharedPreferences.edit().putString(LAST_UPDATED, lastUpdated).apply()
    }

}