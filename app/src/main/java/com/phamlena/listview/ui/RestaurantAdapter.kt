package com.phamlena.listview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.phamlena.listview.R
import com.phamlena.listview.databinding.LayoutItemRestaurantBinding
import com.phamlena.listview.model.Restaurant

class RestaurantAdapter :
    ListAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = LayoutItemRestaurantBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.onBind(restaurant)
    }


    inner class RestaurantViewHolder(private val itemBinding: LayoutItemRestaurantBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(item: Restaurant) {
            itemBinding.tvName.text = item.name
            Glide.with(itemBinding.root.context).load(item.urlAvatar).into(itemBinding.ivAvatar)
            if (item.isOpen == true) {
                itemBinding.tvStatus.text = itemBinding.root.resources.getString(R.string.open)
                itemBinding.cvBackgroundStatus.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemBinding.root.context, R.color.background_status_opening
                    )
                )
            } else {
                itemBinding.tvStatus.text = itemBinding.root.resources.getString(R.string.closed)
                itemBinding.cvBackgroundStatus.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemBinding.root.context, R.color.background_status_close
                    )
                )
            }
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean =
                oldItem == newItem
        }
    }
}