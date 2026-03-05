package com.vahossmedia.android.mylocalrestaurantfinder.ui

import app.cash.turbine.test
import com.vahossmedia.android.mylocalrestaurantfinder.api.BusinessResponse
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business
import com.vahossmedia.android.mylocalrestaurantfinder.model.Category
import com.vahossmedia.android.mylocalrestaurantfinder.model.Coordinates
import com.vahossmedia.android.mylocalrestaurantfinder.model.Location
import com.vahossmedia.android.mylocalrestaurantfinder.fake.FakeRestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BusinessListViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var fakeRepository: FakeRestaurantRepository
    private lateinit var viewModel: BusinessListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeRestaurantRepository()
        viewModel = BusinessListViewModel(fakeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // ── Initial state ──────────────────────────────────────────────────────────

    @Test
    fun `initial uiState is Loading`() {
        assertEquals(RestaurantUiState.Loading, viewModel.uiState.value)
    }

    // ── Coordinates are forwarded correctly ────────────────────────────────────

    @Test
    fun `fetchRestaurants passes coordinates to repository`() = runTest {
        val lat = 49.2827
        val long = -123.1207

        viewModel.fetchRestaurants(Pair(lat, long))

        assertEquals(lat, fakeRepository.lastLatitude!!, 0.0)
        assertEquals(long, fakeRepository.lastLongitude!!, 0.0)
    }

    // ── Success path ───────────────────────────────────────────────────────────

    /**
     * StateFlow dedup note: the VM starts in Loading and re-sets Loading at the
     * top of fetchRestaurants. Because the value is identical, StateFlow does NOT
     * emit a duplicate. Turbine therefore only sees the transition to Success.
     */
    @Test
    fun `uiState emits Success with businesses on successful fetch`() = runTest {
        val businesses = listOf(fakeBusiness())
        fakeRepository.businessesToReturn = BusinessResponse(businesses)

        viewModel.uiState.test {          // opens a Turbine collector on the StateFlow
            // consume initial Loading
            awaitItem()                   // receives the current value immediately (Loading)
            // because StateFlow replays latest to new collectors
            viewModel.fetchRestaurants(Pair(49.2827, -123.1207))
            assertEquals(
                RestaurantUiState.Success(businesses),
                awaitItem()               // waits for the next emission (Success)
            )
            cancelAndIgnoreRemainingEvents()  // closes the collector, discards anything queued
        }

    }

    // ── Error path ─────────────────────────────────────────────────────────────

    @Test
    fun `uiState emits Error with exception message on fetch failure`() = runTest {
        fakeRepository.shouldThrow = true
        fakeRepository.errorMessage = "Network error"

        viewModel.uiState.test {
            awaitItem() // consume initial Loading
            viewModel.fetchRestaurants(Pair(49.2827, -123.1207))
            assertEquals(RestaurantUiState.Error("Network error"), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits Error with fallback message when exception message is null`() = runTest {
        fakeRepository.shouldThrow = true
        fakeRepository.errorMessage = null

        viewModel.uiState.test {
            awaitItem() // consume initial Loading
            viewModel.fetchRestaurants(Pair(49.2827, -123.1207))
            assertEquals(RestaurantUiState.Error("An unknown error occurred"), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    // ── setLocation ────────────────────────────────────────────────────────────

    @Test
    fun `setLocation updates location StateFlow`() = runTest {
        viewModel.setLocation(49.2827, -123.1207)
        assertEquals(Pair(49.2827, -123.1207), viewModel.location.value)
    }

    @Test
    fun `setLocation triggers fetch with provided coordinates and emits Success`() = runTest {
        val lat = 49.2827
        val long = -123.1207
        val businesses = listOf(fakeBusiness())
        fakeRepository.businessesToReturn = BusinessResponse(businesses)

        viewModel.uiState.test {
            awaitItem() // consume initial Loading
            viewModel.setLocation(lat, long)
            assertEquals(RestaurantUiState.Success(businesses), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

        assertEquals(lat, fakeRepository.lastLatitude!!, 0.0)
        assertEquals(long, fakeRepository.lastLongitude!!, 0.0)
    }

    // ── Helpers ────────────────────────────────────────────────────────────────

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
