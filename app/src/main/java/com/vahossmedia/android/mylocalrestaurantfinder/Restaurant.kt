package com.vahossmedia.android.mylocalrestaurantfinder

import java.util.UUID

data class Restaurant(
    // TODO: add primary key for db
    val id: UUID,
    val name: String,
    val description: String,
    val rating: Double,
    val imageUrl: String
    // TODO: add reviews, maybe a review would be a data class on its own?
)
