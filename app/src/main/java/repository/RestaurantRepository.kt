package repository

import com.vahossmedia.android.mylocalrestaurantfinder.api.BusinessResponse


/**
 * Interface defining the contract for restaurant data operations.
 *
 * This abstraction allows us to:
 * 1. Swap implementations (real API vs fake for testing)
 * 2. Add new data sources (Room database) without changing ViewModels
 * 3. Test ViewModels in isolation
 */
interface RestaurantRepository {
    suspend fun fetchBusinesses(
        latitude: Double? = null,
        longitude: Double? = null,
        location: String? = null
    ): BusinessResponse
}