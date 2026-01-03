package com.vahossmedia.android.mylocalrestaurantfinder

import com.vahossmedia.android.mylocalrestaurantfinder.api.YelpFusionApi
import repository.RestaurantRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YelpRepository @Inject constructor(
    private val yelpFusionApi: YelpFusionApi
) : RestaurantRepository {
    override suspend fun fetchBusinesses(
        latitude: Double?,
        longitude: Double?,
        location: String?
    ) = yelpFusionApi.fetchBusinesses(latitude, longitude, location)
}