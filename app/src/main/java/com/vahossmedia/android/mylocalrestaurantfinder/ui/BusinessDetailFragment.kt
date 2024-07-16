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
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.FragmentBusinessDetailBinding
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business
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

    private val businessDetailViewModel: BusinessDetailViewModel by viewModels()

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
                businessDetailViewModel.restaurant.collectLatest {
                    it?.let { it1 -> updateUi(it1) }
                }
            }
        }
    }

    private fun updateUi(business: Business) {
        binding.apply {
            binding.businessName.text = business.name
            binding.businessRating.rating = business.rating.toFloat()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
