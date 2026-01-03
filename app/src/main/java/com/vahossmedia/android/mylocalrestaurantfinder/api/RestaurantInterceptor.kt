package com.vahossmedia.android.mylocalrestaurantfinder.api

import com.vahossmedia.android.mylocalrestaurantfinder.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

const val API_KEY = BuildConfig.API_KEY

class RestaurantInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        val newUrl: HttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("sort_by", "best_match")
            .addQueryParameter("limit", "20")
            .build()

        val newRequest: Request = originalRequest.newBuilder()
            .header("Authorization", "Bearer $API_KEY")
            .addHeader("accept", "application/json")
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
