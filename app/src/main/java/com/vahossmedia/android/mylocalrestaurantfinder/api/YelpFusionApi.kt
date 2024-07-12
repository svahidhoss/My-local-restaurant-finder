package com.vahossmedia.android.mylocalrestaurantfinder.api

import retrofit2.http.GET

interface YelpFusionApi {
    @GET("businesses/search?sort_by=best_match")
    suspend fun fetchBusinesses(): BusinessResponse
}