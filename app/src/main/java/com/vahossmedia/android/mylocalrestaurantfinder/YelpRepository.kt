package com.vahossmedia.android.mylocalrestaurantfinder

import com.vahossmedia.android.mylocalrestaurantfinder.api.YelpFusionApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YelpRepository @Inject constructor(private val yelpFusionApi: YelpFusionApi) {
    suspend fun fetchBusinesses(
        latitude: Double? = null,
        longitude: Double? = null,
        location: String? = null
    ) = yelpFusionApi.fetchBusinesses(latitude, longitude, location)
}
