package com.co.ceiba.domain.models

import java.util.*

class Coordinator {
    companion object {
        private const val millisPerDay = 3600000
        fun isUpdated(storeDate: String): Boolean {
            val actualDate = Calendar.getInstance().timeInMillis
            return if (storeDate.isNotEmpty()) {
                (actualDate - storeDate.toLong()) <= millisPerDay
            } else {
                false
            }
        }

    }
}