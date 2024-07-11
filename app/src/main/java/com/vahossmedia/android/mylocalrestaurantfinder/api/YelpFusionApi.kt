package com.vahossmedia.android.mylocalrestaurantfinder.api

import retrofit2.http.GET

interface YelpFusionApi {
    @GET("businesses/search?sort_by=best_match&limit=20")
    suspend fun fetchRestaurants(): String
}