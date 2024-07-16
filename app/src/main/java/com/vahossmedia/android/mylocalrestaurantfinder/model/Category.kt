package com.vahossmedia.android.mylocalrestaurantfinder.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val title: String,
    val alias: String = "",
) : Parcelable
