package com.vahossmedia.android.mylocalrestaurantfinder

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vahossmedia.android.mylocalrestaurantfinder.api.RestaurantInterceptor
import com.vahossmedia.android.mylocalrestaurantfinder.api.YelpFusionApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.yelp.com/v3/"

object YelpService {

    /**
     * Creates an instance of [YelpFusionApi].
     */
    private fun provideYelpService(): YelpFusionApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RestaurantInterceptor())
            .build()

        // Kotlin reflection adapter
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()

        return retrofit.create(YelpFusionApi::class.java)
    }

    private var YELP_SERVICE: YelpFusionApi? = null

    fun getYelpService(): YelpFusionApi {
        if (YELP_SERVICE == null) YELP_SERVICE = provideYelpService()
        return YELP_SERVICE!!
    }
}
