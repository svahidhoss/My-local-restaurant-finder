package com.vahossmedia.android.mylocalrestaurantfinder.api

import retrofit2.http.GET

interface YelpFusionApi {
    @GET("businesses/search")
    suspend fun fetchBusinesses(): BusinessResponse
}