package com.vahossmedia.android.mylocalrestaurantfinder.model

import com.squareup.moshi.Json

data class Business(
    @Json(name = "id") val id: String,
    @Json(name = "alias") val alias: String,
    @Json(name = "name") val name: String,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "is_closed") val isClosed: Boolean,
    @Json(name = "url") val url: String,
    @Json(name = "review_count") val reviewCount: Int,
    @Json(name = "categories") val categories: List<Category>,
    @Json(name = "rating") val rating: Double,
    val coordinates: Coordinates,
    val transactions: List<String>,
    val price: String,
    val location: Location,
    val phone: String,
    @Json(name = "display_phone") val displayPhone: String,
    val distance: Double
)
