package com.phamlena.listview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.phamlena.listview.databinding.FragmentRestaurantBinding
import com.phamlena.listview.viewmodel.MainViewModel

class RestaurantFragment : Fragment() {

    private lateinit var binding: FragmentRestaurantBinding
    private lateinit var mainViewModel: MainViewModel
    private var offset = 0
    private val restaurantAdapter: RestaurantAdapter by lazy {
        RestaurantAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.srlMain.setOnRefreshListener {
            offset = 0
            getRestaurants()
        }

        binding.rcvRestaurant.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvRestaurant.adapter = restaurantAdapter

        binding.cvLoadMore.setOnClickListener {
            getRestaurants()
        }

        mainViewModel.restaurants.observe(viewLifecycleOwner) { restaurants ->
            restaurantAdapter.submitList(restaurants)
            binding.srlMain.isRefreshing = false
        }
        getRestaurants()
    }

    private fun getRestaurants() {
        mainViewModel.getRestaurants(offset)
        offset += 10
    }

}