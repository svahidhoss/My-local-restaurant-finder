package com.vahossmedia.android.mylocalrestaurantfinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vahossmedia.android.mylocalrestaurantfinder.databinding.ListItemRestaurantBinding

class RestaurantListAdapter(
    private val restaurants: List<Restaurant>,
    private val onRestaurantClicked: () -> Unit
) : RecyclerView.Adapter<RestaurantHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemRestaurantBinding.inflate(inflater, parent, false)
        return RestaurantHolder(binding)
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: RestaurantHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant, onRestaurantClicked)
    }

}

class RestaurantHolder(private val binding: ListItemRestaurantBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(restaurant: Restaurant, onRestaurantClicked: () -> Unit) {
        binding.restaurantName.text = restaurant.name
        binding.restaurantRating.rating = restaurant.rating.toFloat()
        // tODO set image using glide

        binding.root.setOnClickListener {
            onRestaurantClicked()
        }
    }

}