package com.co.ceiba.infrastructure.httpclient.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatesNetwork(
    @SerializedName ("maximum") val maximum: String,
    @SerializedName ("minimum") val minimum: String
) : Parcelable {

}
