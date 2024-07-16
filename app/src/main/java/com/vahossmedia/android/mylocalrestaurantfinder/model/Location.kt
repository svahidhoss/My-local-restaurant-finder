package com.vahossmedia.android.mylocalrestaurantfinder.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val address1: String,
    val address2: String? = "",
    val address3: String? = "",
    val city: String,
    @Json(name = "zip_code") val zipCode: String,
    val country: String,
    val state: String,
    @Json(name = "display_address") val displayAddress: List<String> = emptyList()
) : Parcelable

