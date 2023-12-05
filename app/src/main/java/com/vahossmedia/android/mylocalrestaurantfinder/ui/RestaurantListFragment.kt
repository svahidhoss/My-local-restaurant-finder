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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vahossmedia.android.mylocalrestaurantfinder.R
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.FragmentRestaurantListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

    private val restaurantListViewModel: RestaurantListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using binding
        _binding = FragmentRestaurantListBinding.inflate(layoutInflater, container, false)

        // Setup layout manager
        binding.restaurantRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                restaurantListViewModel.restaurants.collectLatest {
                    binding.restaurantRecyclerView.adapter = RestaurantListAdapter(it)
                    {
                        findNavController().navigate(
                            RestaurantListFragmentDirections.actionShowRestaurantDetail()
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
