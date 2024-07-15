package com.vahossmedia.android.mylocalrestaurantfinder.model

import com.squareup.moshi.Json

data class Business(
    val id: String,
    val alias: String?,
    val name: String,
    @Json(name = "image_url") val imageUrl: String?,
    @Json(name = "is_closed") val isClosed: Boolean,
    val description: String? = null,
    val rating: Double = 0.0,
    val url: String?,
    @Json(name = "review_count") val reviewCount: Int?,
    val categories: List<Category>?,
    val coordinates: Coordinates?,
    val transactions: List<String>?,
    val price: String?,
    val location: Location?,
    val phone: String?,
    @Json(name = "display_phone") val displayPhone: String?,
    val distance: Double?
)
