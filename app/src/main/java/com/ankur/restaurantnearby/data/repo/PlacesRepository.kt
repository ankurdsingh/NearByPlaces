package com.ankur.restaurantnearby.data.repo

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ankur.restaurantnearby.data.NearbyResponseModel
import com.ankur.restaurantnearby.data.PlaceApiService
import com.ankur.restaurantnearby.data.RestaurantModel
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class PlacesRepository {
    val restaurantData = MutableLiveData<List<RestaurantModel>>()

    fun findPlaces(location: Location, apiKey: String){
        val placeApiService = PlaceApiService()
        val call = placeApiService.getRestaurants(location,apiKey)
        Log.d("Restaurants URL: ", call.request().url().toString())
        call.enqueue(object : Callback,
            retrofit2.Callback<NearbyResponseModel> {
            override fun onFailure(call: Call<NearbyResponseModel>, t: Throwable) {
                Log.d("Restaurants error: ", t.toString())
            }

            override fun onResponse(
                call: Call<NearbyResponseModel>,
                response: Response<NearbyResponseModel>
            ) {
                Log.d("Restaurants: ", response.toString())
                val nearbyResponseModel =  response.body()
                if(nearbyResponseModel!=null && nearbyResponseModel.status=="OK") {
                    restaurantData.postValue(nearbyResponseModel.results)
                }
            }
        })

    }

}