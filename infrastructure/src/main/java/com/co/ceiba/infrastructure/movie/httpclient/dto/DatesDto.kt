package com.co.ceiba.infrastructure.movie.httpclient.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatesDto(
    @SerializedName ("maximum") val maximum: String,
    @SerializedName ("minimum") val minimum: String
) : Parcelable
