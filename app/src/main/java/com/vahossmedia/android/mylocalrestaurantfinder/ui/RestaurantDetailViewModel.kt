package com.vahossmedia.android.mylocalrestaurantfinder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahossmedia.android.mylocalrestaurantfinder.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class RestaurantDetailViewModel : ViewModel() {
    private val _restaurant = MutableStateFlow<Restaurant?>(null)

    val restaurant
        get() = _restaurant.asStateFlow()

    init {
        val mockRestaurant = Restaurant(
            UUID.randomUUID(),
            "Yaas Detail Mock",
            "A Persian grill house",
            2.5,
            ""
        )

        viewModelScope.launch {
            _restaurant.emit(mockRestaurant)
        }
    }
}
