package com.vahossmedia.android.mylocalrestaurantfinder.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Category(
    val title: String,
    val alias: String = "",
) : Parcelable
