package com.vahossmedia.android.mylocalrestaurantfinder.fake

import com.vahossmedia.android.mylocalrestaurantfinder.api.BusinessResponse
import com.vahossmedia.android.mylocalrestaurantfinder.repository.RestaurantRepository

class FakeRestaurantRepository : RestaurantRepository {

    var businessesToReturn: BusinessResponse = BusinessResponse(emptyList())
    var shouldThrow: Boolean = false
    var errorMessage: String? = null

    var lastLatitude: Double? = null
    var lastLongitude: Double? = null
    var lastLocation: String? = null

    override suspend fun fetchBusinesses(
        latitude: Double?,
        longitude: Double?,
        location: String?
    ): BusinessResponse {
        lastLatitude = latitude
        lastLongitude = longitude
        lastLocation = location
        if (shouldThrow) throw Exception(errorMessage)
        return businessesToReturn
    }
}