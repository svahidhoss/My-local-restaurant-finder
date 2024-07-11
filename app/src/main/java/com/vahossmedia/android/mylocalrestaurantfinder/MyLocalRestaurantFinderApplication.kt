package com.vahossmedia.android.mylocalrestaurantfinder

import android.app.Application

class MyLocalRestaurantFinderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // init yelp service
        YelpService.getYelpService()
    }
}