package com.vahossmedia.android.mylocalrestaurantfinder.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val title: String,
    val alias: String = "",
) : Parcelable
