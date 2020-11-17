package com.ankur.restaurantnearby.data

data class NearbyResponseModel(val results : List<RestaurantModel>,
                               val status: String)