package com.vahossmedia.android.mylocalrestaurantfinder.ui

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.FragmentRestaurantListBinding
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val TAG = "RestaurantListFragment"
const val LOCATION_PERMISSION_REQUEST_CODE = 1001

/**
 * A simple [Fragment] subclass that displays
 * the list of restaurants.
 */
@AndroidEntryPoint
class BusinessListFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var _binding: FragmentRestaurantListBinding? = null

    // Make binding a computed value
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val businessListViewModel: BusinessListViewModel by viewModels()

    private val businessListAdapter = BusinessListAdapter { business ->
        findNavController().navigate(
            BusinessListFragmentDirections.actionShowRestaurantDetail(business)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using binding
        _binding = FragmentRestaurantListBinding.inflate(layoutInflater, container, false)

        // Setup layout manager and adapter
        binding.restaurantRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.restaurantRecyclerView.adapter = businessListAdapter

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                businessListViewModel.uiState.collect { state ->
                    updateUi(state)
                }
            }
        }

        // Location permission flow
        if (checkLocationPermission()) {
            if (isLocationEnabled()) getLastLocation()
            else {
                showLocationDisabledDialog()
                // Use a default location
                businessListViewModel.fetchRestaurants()
            }
        } else requestLocationPermission()
    }

    private fun updateUi(state: RestaurantUiState) {
        when (state) {
            is RestaurantUiState.Loading -> {
                binding.loadingProgressBar.isVisible = true
                binding.restaurantRecyclerView.isVisible = false
                binding.errorTextView.isVisible = false
            }

            is RestaurantUiState.Success -> {
                binding.loadingProgressBar.isVisible = false
                binding.restaurantRecyclerView.isVisible = true
                binding.errorTextView.isVisible = false
                // Update your RecyclerView with the data
                showRestaurants(state.restaurants)
            }

            is RestaurantUiState.Error -> {
                binding.loadingProgressBar.isVisible = false
                binding.restaurantRecyclerView.isVisible = false
                binding.errorTextView.isVisible = true
                binding.errorTextView.text = state.message
            }
        }
    }

    private fun showRestaurants(restaurants: List<Business>) {
        businessListAdapter.submitList(restaurants)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (isLocationEnabled()) getLastLocation()
                else {
                    showLocationDisabledDialog()
                    businessListViewModel.fetchRestaurants()
                }
            } else {
                businessListViewModel.fetchRestaurants()
            }
        }
    }

    private fun checkLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    Log.d(TAG, "Location received with ${location.latitude} and ${location.longitude}")
                    businessListViewModel.setLocation(location.latitude, location.longitude)
                } else {
                    businessListViewModel.fetchRestaurants()
                }
            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun showLocationDisabledDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Location Services Disabled")
            .setMessage("Please enable location services to use this feature.")
            .setPositiveButton("Settings") { _, _ ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}
