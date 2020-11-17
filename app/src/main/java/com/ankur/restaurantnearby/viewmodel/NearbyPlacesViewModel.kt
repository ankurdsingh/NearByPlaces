package com.ankur.restaurantnearby.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ankur.restaurantnearby.data.NearbyResponseModel
import com.ankur.restaurantnearby.data.RestaurantModel
import com.ankur.restaurantnearby.data.repo.PlacesRepository
import javax.inject.Inject

class NearbyPlacesViewModel @Inject constructor(val  placesRepository: PlacesRepository):ViewModel() {
    val currentLocation = MutableLiveData<Location>()
    val restaurantData : LiveData<List<RestaurantModel>>
    get() = placesRepository.restaurantData
    fun updateCurrentLocation(location: Location, apiKey : String){
        currentLocation.value = location
        placesRepository.findPlaces(location,apiKey)
    }
}