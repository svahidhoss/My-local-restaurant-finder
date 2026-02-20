package com.vahossmedia.android.mylocalrestaurantfinder.di

import com.vahossmedia.android.mylocalrestaurantfinder.repository.YelpRepository
import com.vahossmedia.android.mylocalrestaurantfinder.repository.RestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRestaurantRepository(
        yelpRepository: YelpRepository
    ): RestaurantRepository
}