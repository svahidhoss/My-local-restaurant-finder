package com.vahossmedia.android.mylocalrestaurantfinder.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business

@JsonClass(generateAdapter = true)
data class BusinessResponse(
    @Json(name = "businesses") val businesses: List<Business>
)