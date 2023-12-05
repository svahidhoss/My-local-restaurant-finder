package com.vahossmedia.android.mylocalrestaurantfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.FragmentRestaurantListBinding

/**
 * A simple [Fragment] subclass that displays
 * the list of restaurants.
 */
class RestaurantListFragment : Fragment() {

    private var _binding: FragmentRestaurantListBinding? = null

    // Make binding a computed value
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using binding
        _binding = FragmentRestaurantListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
