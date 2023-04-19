package com.phamlena.listview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.phamlena.listview.R
import com.phamlena.listview.model.Restaurant
import kotlin.random.Random

class MainViewModel : ViewModel() {

    val restaurants = MutableLiveData<List<Restaurant>>()

    private val mockRestaurants = mockData()

    private fun mockData(): List<Restaurant> {

        val urlResIds = listOf(
            R.drawable.restaurant,
            R.drawable.restaurant_1,
            R.drawable.restaurant_2,
            R.drawable.restaurant_3,
        )

        val restaurants = ArrayList<Restaurant>()
        (1..30).forEach {
            restaurants.add(
                Restaurant(
                    "Restaurant $it",
                    urlAvatar = urlResIds[Random.nextInt(0, 3)],
                    Random.nextBoolean()
                )
            )
        }

        return restaurants
    }

    fun getRestaurants(offset: Int) {
        if (mockRestaurants.size - offset > 10) {
            restaurants.value = mockRestaurants.slice(0..offset - 1 + 10)
        } else {
            restaurants.value = mockRestaurants
        }
    }

}