package com.vahossmedia.android.mylocalrestaurantfinder.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.vahossmedia.android.mylocalrestaurantfinder.R
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.FragmentBusinessDetailBinding
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Represents Detail view of selected restaurant.
 */
@AndroidEntryPoint
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
                businessDetailViewModel.businessState.collectLatest {
                    updateUi(it)
                }
            }
        }

        binding.businessImage.setOnClickListener { openBusinessUrl() }
        binding.businessName.setOnClickListener { openBusinessUrl() }
    }

    private fun openBusinessUrl() {
        businessDetailViewModel.businessState.value.url?.let {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(intent)
        } ?: run {
            // Handle case where URL is null
            Toast.makeText(
                context,
                getString(R.string.error_business_url_not_available),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateUi(business: Business) {
        binding.apply {
            binding.businessName.text = business.name
            binding.businessRating.rating = business.rating.toFloat()
            binding.businessDescription.text = business.location?.address1
            binding.businessImage.load(business.imageUrl)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
