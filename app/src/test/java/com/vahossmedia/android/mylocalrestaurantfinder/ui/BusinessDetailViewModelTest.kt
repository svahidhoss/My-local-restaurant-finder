package com.vahossmedia.android.mylocalrestaurantfinder.ui

import androidx.lifecycle.SavedStateHandle
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business
import com.vahossmedia.android.mylocalrestaurantfinder.model.Category
import com.vahossmedia.android.mylocalrestaurantfinder.model.Coordinates
import com.vahossmedia.android.mylocalrestaurantfinder.model.Location
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class BusinessDetailViewModelTest {

    @Test
    fun `businessState exposes the business from SavedStateHandle`() {
        val business = fakeBusiness()
        val viewModel = BusinessDetailViewModel(
            SavedStateHandle(mapOf("business" to business))
        )

        assertEquals(business, viewModel.businessState.value)
    }

    @Test
    fun `throws IllegalArgumentException when business argument is missing`() {
        assertThrows(IllegalArgumentException::class.java) {
            BusinessDetailViewModel(SavedStateHandle())
        }
    }

    private fun fakeBusiness() = Business(
        id = "1",
        alias = "test-restaurant",
        name = "Test Restaurant",
        imageUrl = null,
        isClosed = false,
        url = null,
        reviewCount = 100,
        categories = listOf(Category(title = "Restaurants")),
        rating = 4.5,
        coordinates = Coordinates(latitude = 49.2827, longitude = -123.1207),
        transactions = null,
        price = "$$",
        location = Location(
            address1 = "123 Test St",
            city = "Vancouver",
            state = "BC",
            zipCode = "V6B 1A1",
            country = "CA"
        ),
        phone = "+16041234567",
        displayPhone = "(604) 123-4567",
        distance = 1.0
    )
}