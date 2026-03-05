package com.vahossmedia.android.mylocalrestaurantfinder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahossmedia.android.mylocalrestaurantfinder.repository.RestaurantRepository
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessListViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RestaurantUiState>(RestaurantUiState.Loading)
    val uiState: StateFlow<RestaurantUiState> = _uiState.asStateFlow()

    private val _location = MutableStateFlow<Pair<Double, Double>?>(null)
    val location: StateFlow<Pair<Double, Double>?> = _location.asStateFlow()

    fun fetchRestaurants(pair: Pair<Double, Double>? = null) {
        viewModelScope.launch {
            _uiState.value = RestaurantUiState.Loading
            try {
                val response =
                    if (pair == null) restaurantRepository.fetchBusinesses(location = "Vancouver")
                    else restaurantRepository.fetchBusinesses(pair.first, pair.second)
                _uiState.value = RestaurantUiState.Success(response.businesses)
            } catch (e: Exception) {
                _uiState.value = RestaurantUiState.Error(e.message ?: "An unknown error occurred")
            }
        }
    }

    fun setLocation(latitude: Double, longitude: Double) {
        _location.value = Pair(latitude, longitude)
        viewModelScope.launch {
            fetchRestaurants(_location.value)
        }
    }
}

sealed class RestaurantUiState {
    data object Loading : RestaurantUiState()
    data class Success(val restaurants: List<Business>) : RestaurantUiState()
    data class Error(val message: String) : RestaurantUiState()
}
