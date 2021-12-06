package com.co.ceiba.infrastructure.httpclient.model

import android.os.Parcelable
import com.co.ceiba.domain.models.Movie
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesNetwork(
    @SerializedName("dates") val dates: DatesNetwork,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieNetwork>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
) : Parcelable{
    fun map ():List<Movie> {
        return results.map {
            it.map()
        }
    }
}
