package com.vahossmedia.android.mylocalrestaurantfinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.ListItemBusinessBinding
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business

class BusinessListAdapter(
    private val businesses: List<Business>,
    private val onBusinessClicked: () -> Unit
) : RecyclerView.Adapter<BusinessHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBusinessBinding.inflate(inflater, parent, false)
        return BusinessHolder(binding)
    }

    override fun getItemCount() = businesses.size

    override fun onBindViewHolder(holder: BusinessHolder, position: Int) {
        val business = businesses[position]
        holder.bind(business, onBusinessClicked)
    }
}

class BusinessHolder(private val binding: ListItemBusinessBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(business: Business, onBusinessClicked: () -> Unit) {
        binding.businessName.text = business.name
        binding.businessDescription.text = business.location?.address1
        binding.businessRating.rating = business.rating.toFloat()
        // load image using coil
        binding.restaurantImage.load(business.imageUrl)

        binding.root.setOnClickListener {
            onBusinessClicked()
        }
    }
}
