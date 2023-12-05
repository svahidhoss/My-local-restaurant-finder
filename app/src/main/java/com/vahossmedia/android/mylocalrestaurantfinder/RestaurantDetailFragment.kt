package com.vahossmedia.android.mylocalrestaurantfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.FragmentRestaurantDetailBinding

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
