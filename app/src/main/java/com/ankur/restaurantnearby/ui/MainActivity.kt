package com.ankur.restaurantnearby.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ankur.restaurantnearby.R
import com.ankur.restaurantnearby.databinding.ActivityMainBinding
import com.ankur.restaurantnearby.util.ViewModelProvideFactory
import com.ankur.restaurantnearby.viewmodel.NearbyPlacesViewModel
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


/**
 * A Simple Subclass of [DaggerAppCompatActivity]
 * It will host two fragment for one will display list
 * and another will display map view
 */
class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelProvideFactory: ViewModelProvideFactory
    private var mLastLocation : Location? = null
    private lateinit var binding : ActivityMainBinding
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    lateinit var nearbyPlacesViewModel: NearbyPlacesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this
        initObjects()
        checkForLocationEnable()
    }

    private fun initObjects() {
        nearbyPlacesViewModel = ViewModelProvider(this,viewModelProvideFactory).get(NearbyPlacesViewModel::class.java)
    }

    private fun checkForLocationEnable() {
        val builder =
            LocationSettingsRequest.Builder().addLocationRequest(getLocationRequest())

        val settingsClient = LocationServices.getSettingsClient(this@MainActivity)
        val task =
            settingsClient.checkLocationSettings(builder.build())
        task.addOnSuccessListener(this@MainActivity) {
            getDeviceLocation()
        }

        task.addOnFailureListener(this@MainActivity
        ) { e ->
            if (e is ResolvableApiException) {
                try {
                    e.startResolutionForResult(this@MainActivity, 51)
                } catch (e1: SendIntentException) {
                    e1.printStackTrace()
                }
            }
        }
    }

    private fun getLocationRequest():LocationRequest{
        //check if gps is enabled or not and then request user to enable it
        //check if gps is enabled or not and then request user to enable it
        val locationRequest = LocationRequest.create()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        return locationRequest
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 51) {
            if (resultCode == Activity.RESULT_OK) {
                getDeviceLocation()
            }
        }
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            mLastLocation = locationResult.lastLocation
            nearbyPlacesViewModel.updateCurrentLocation(locationResult.lastLocation, getString(R.string.google_maps_api))
        }

    }

    private fun getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this@MainActivity)
        mFusedLocationProviderClient?.let {fusedLocationProviderClient ->
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        mLastLocation = task.result
                        mLastLocation?.let {
                            nearbyPlacesViewModel.updateCurrentLocation(it, getString(R.string.google_maps_api))
                        }
                        if(mLastLocation==null){
                            mFusedLocationProviderClient?.requestLocationUpdates(
                                getLocationRequest(),
                                locationCallback,
                                null
                            )
                            mFusedLocationProviderClient?.removeLocationUpdates(locationCallback)
                        }
                }
                else{
                    Toast.makeText(this@MainActivity, getString(R.string.unable_to_get_location), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

}