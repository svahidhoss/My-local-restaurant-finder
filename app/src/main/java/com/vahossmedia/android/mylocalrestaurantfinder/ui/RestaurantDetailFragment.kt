package com.vahossmedia.android.mylocalrestaurantfinder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vahossmedia.android.mylocalrestaurantfinder.Restaurant
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.FragmentRestaurantDetailBinding
import java.util.UUID

/**
 * Represents Detail view of selected restaurant.
 */
class RestaurantDetailFragment : Fragment() {

    private var _binding: FragmentRestaurantDetailBinding? = null

    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using binding
        _binding = FragmentRestaurantDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurant = Restaurant(
            UUID.randomUUID(),
            "Yaas",
            "A Persian grill house",
            4.0,
            ""
        )

        updateUi(restaurant)
    }

    private fun updateUi(restaurant: Restaurant) {
        binding.apply {
            binding.restaurantName.text = restaurant.name
            binding.restaurantRating.rating = restaurant.rating.toFloat()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
