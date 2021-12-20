package com.co.ceiba.domain.repositories

interface MovieTemporalRepository {
    fun getLastUpdatedPreference (): String
    fun saveLastUpdatedPreference (lastUpdated: String)
}