package com.ankur.restaurantnearby.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NearbyPlaceApi {
    @GET("place/nearbysearch/json")
    fun getRestaurants(@QueryMap param : Map<String,String>): Call<NearbyResponseModel>
}