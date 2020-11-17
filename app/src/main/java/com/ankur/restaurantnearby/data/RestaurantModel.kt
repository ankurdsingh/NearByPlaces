package com.ankur.restaurantnearby.data

data class RestaurantModel(val place_id: String,
                           val icon: String,
                           val name: String,
                           val rating: Double,
                           val vicinity : String)