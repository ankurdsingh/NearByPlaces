package com.ankur.restaurantnearby.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ankur.restaurantnearby.R
import com.ankur.restaurantnearby.data.RestaurantModel
import com.ankur.restaurantnearby.databinding.LayoutSingleItemRestaurantBinding
import com.ankur.restaurantnearby.ui.PlacesViewHolder

class PlacesAdapter(placesList : List<RestaurantModel>):RecyclerView.Adapter<PlacesViewHolder>() {
    private val mPlacesDataList = mutableListOf<RestaurantModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutSingleItemRestaurantBinding>(
            inflater, R.layout.layout_single_item_restaurant,parent,false)
        return PlacesViewHolder(binding)
    }

    override fun getItemCount()= mPlacesDataList.size
    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.bindding.place = mPlacesDataList[position]
    }
    fun updateList(placesDataList: List<RestaurantModel>){
        this.mPlacesDataList.clear()
        this.mPlacesDataList.addAll(placesDataList)
        notifyDataSetChanged()
    }
}