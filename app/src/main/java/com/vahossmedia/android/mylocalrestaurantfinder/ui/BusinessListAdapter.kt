package com.vahossmedia.android.mylocalrestaurantfinder.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.ListItemBusinessBinding
import com.vahossmedia.android.mylocalrestaurantfinder.model.Business

class BusinessListAdapter(
    private val onBusinessClicked: (business: Business) -> Unit
) : ListAdapter<Business, BusinessHolder>(BusinessDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBusinessBinding.inflate(inflater, parent, false)
        return BusinessHolder(binding)
    }

    override fun onBindViewHolder(holder: BusinessHolder, position: Int) {
        holder.bind(getItem(position), onBusinessClicked)
    }
}

object BusinessDiffCallback : DiffUtil.ItemCallback<Business>() {
    override fun areItemsTheSame(oldItem: Business, newItem: Business): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Business, newItem: Business): Boolean {
        return oldItem == newItem
    }
}

class BusinessHolder(private val binding: ListItemBusinessBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(business: Business, onBusinessClicked: (business: Business) -> Unit) {
        binding.businessName.text = business.name
        binding.businessDescription.text = business.location?.address1
        binding.businessRating.rating = business.rating.toFloat()
        // load image using coil
        binding.businessImage.load(business.imageUrl)

        binding.root.setOnClickListener {
            onBusinessClicked(business)
        }
    }
}