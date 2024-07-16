package com.vahossmedia.android.mylocalrestaurantfinder.api

import retrofit2.http.GET
import retrofit2.http.Query

interface YelpFusionApi {
    @GET("businesses/search")
    suspend fun fetchBusinesses(
        @Query("latitude") latitude: Double? = null,
        @Query("longitude") longitude: Double? = null
    ): BusinessResponse
}
