package com.vahossmedia.android.mylocalrestaurantfinder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.vahossmedia.android.mylocalrestaurantfinder.Restaurant
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.FragmentBusinessDetailBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Represents Detail view of selected restaurant.
 */
class BusinessDetailFragment : Fragment() {

    private var _binding: FragmentBusinessDetailBinding? = null

    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val restaurantDetailViewModel: RestaurantDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using binding
        _binding = FragmentBusinessDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                restaurantDetailViewModel.restaurant.collectLatest {
                    it?.let { it1 -> updateUi(it1) }
                }
            }
        }
    }

    private fun updateUi(restaurant: Restaurant) {
        binding.apply {
            binding.businessName.text = restaurant.name
            binding.businessRating.rating = restaurant.rating.toFloat()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
