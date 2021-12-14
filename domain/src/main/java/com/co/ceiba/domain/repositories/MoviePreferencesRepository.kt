package com.co.ceiba.domain.repositories

interface MoviePreferencesRepository {
    fun getLastUpdatedPreference (): String
    fun saveLastUpdatedPreference (lastUpdated: String)
}