package com.vahossmedia.android.mylocalrestaurantfinder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business
import com.vahossmedia.android.mylocalrestaurantfinder.model.Category
import com.vahossmedia.android.mylocalrestaurantfinder.model.Coordinates
import com.vahossmedia.android.mylocalrestaurantfinder.model.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BusinessDetailViewModel : ViewModel() {
    private val _business = MutableStateFlow<Business?>(null)

    val restaurant
        get() = _business.asStateFlow()

    init {
        val business = Business(
            id = "1001",
            alias = "lonsdale-quay-market",
            name = "Lonsdale Quay Market",
            imageUrl = "https://example.com/lonsdale-quay.jpg",
            isClosed = false,
            url = "https://lonsdalequay.com",
            reviewCount = 800,
            categories = listOf(Category("market"), Category("shopping")),
            rating = 4.2,
            coordinates = Coordinates(49.3098, -123.0827),
            transactions = null,
            price = "$$",
            location = Location(
                "123 Carrie Cates Court",
                city = "North Vancouver",
                state = "BC",
                zipCode = "V7M 3K7",
                country = "Canada"
            ),
            phone = "+16049864483",
            displayPhone = "(604) 986-4483",
            distance = 0.5,
            description = "Waterfront market with local vendors, restaurants, and shops."
        )

        viewModelScope.launch {
            _business.emit(business)
        }
    }
}
