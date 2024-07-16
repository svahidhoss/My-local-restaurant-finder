package com.vahossmedia.android.mylocalrestaurantfinder

import com.vahossmedia.android.mylocalrestaurantfinder.api.YelpFusionApi

class YelpRepository(private val yelpFusionApi: YelpFusionApi) {
    suspend fun fetchBusinesses(
        latitude: Double? = null,
        longitude: Double? = null
    ) = yelpFusionApi.fetchBusinesses(latitude, longitude)
}
