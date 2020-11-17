package com.ankur.restaurantnearby.data

import android.location.Location
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PlaceApiService {
    private val baseUrl = "https://maps.googleapis.com/maps/api/"

    private val api = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(NearbyPlaceApi::class.java)

    fun getRestaurants(location : Location, apiKey : String) : Call<NearbyResponseModel> {
        val map  = HashMap<String, String>()
        map.put("location", "${location.latitude},${location.longitude}")
        map.put("radius", "5000")//5km
        map.put("type","restaurant")
        map.put("key",apiKey)
        return api.getRestaurants(map)
    }

}