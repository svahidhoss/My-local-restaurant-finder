package com.vahossmedia.android.mylocalrestaurantfinder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class RestaurantListViewModel : ViewModel() {

    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())

    val restaurants
        get() = _restaurants.asStateFlow()

    init {
        val mockCrimes = mutableListOf<Restaurant>()
        for (i in 1 until 50) {
            val restaurant = Restaurant(
                UUID.randomUUID(),
                "Yaas $i",
                "A Persian grill house",
                4.5,
                ""
            )
            mockCrimes.add(restaurant)
        }

        viewModelScope.launch {
            updateRestaurants(mockCrimes)
        }
    }

    private suspend fun updateRestaurants(newItems: List<Restaurant>) {
        _restaurants.emit(newItems)
    }
}