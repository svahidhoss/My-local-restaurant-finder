package com.vahossmedia.android.mylocalrestaurantfinder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BusinessDetailViewModel(business: Business) : ViewModel() {
    private val _business = MutableStateFlow<Business?>(null)

    val restaurant
        get() = _business.asStateFlow()

    init {
        viewModelScope.launch {
            _business.emit(business)
        }
    }
}

class BusinessDetailViewModelFactory(private val business: Business) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BusinessDetailViewModel(business) as T
    }
}