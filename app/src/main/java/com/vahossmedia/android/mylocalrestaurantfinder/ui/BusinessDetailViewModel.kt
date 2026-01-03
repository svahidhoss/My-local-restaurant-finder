package com.vahossmedia.android.mylocalrestaurantfinder.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BusinessDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val business: Business = savedStateHandle.get<Business>("business")
        ?: throw IllegalArgumentException("Business argument is required")

    private val _businessState = MutableStateFlow(business)
    val businessState = _businessState.asStateFlow()
}
