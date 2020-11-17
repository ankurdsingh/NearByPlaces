package com.ankur.restaurantnearby.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankur.restaurantnearby.R
import com.ankur.restaurantnearby.databinding.FragmentRestaurantListBinding
import com.ankur.restaurantnearby.ui.adapter.PlacesAdapter
import com.ankur.restaurantnearby.util.ViewModelProvideFactory
import com.ankur.restaurantnearby.viewmodel.NearbyPlacesViewModel
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.android.support.DaggerFragment
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * It will display list of nearby restaurants
 */
class RestaurantListFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelProvideFactory: ViewModelProvideFactory
    lateinit var nearbyPlacesViewModel: NearbyPlacesViewModel
    private var placesClient: PlacesClient? = null
    private lateinit var binding: FragmentRestaurantListBinding
    private lateinit var placeAdapter : PlacesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_restaurant_list, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity.let { activity ->
            activity?.let {
                nearbyPlacesViewModel = ViewModelProvider(
                    it,
                    viewModelProvideFactory
                ).get(NearbyPlacesViewModel::class.java)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initObjects()
    }

    private fun initObjects() {
        placeAdapter = PlacesAdapter(mutableListOf())
        binding.rvPlaces.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = placeAdapter
        }
    }

    private fun observeViewModel() {
        if(::nearbyPlacesViewModel.isInitialized) {
            nearbyPlacesViewModel.currentLocation.observe(viewLifecycleOwner, Observer {
            })
            nearbyPlacesViewModel.restaurantData.observe(viewLifecycleOwner, Observer {places->
                places?.let {
                    Log.d("Restaurant", it.toString())
                    placeAdapter.updateList(places)
                }
            })
        }
    }

}