package com.vahossmedia.android.mylocalrestaurantfinder

import com.vahossmedia.android.mylocalrestaurantfinder.api.YelpFusionApi

class Repository(private val yelpFusionApi: YelpFusionApi) {

    suspend fun fetchRestaurants() = yelpFusionApi.fetchBusinesses()
}