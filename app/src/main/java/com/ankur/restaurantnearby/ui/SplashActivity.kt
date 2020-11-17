package com.ankur.restaurantnearby.ui

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ankur.restaurantnearby.R
import com.ankur.restaurantnearby.util.SplashEvent
import com.ankur.restaurantnearby.util.ViewModelProvideFactory
import com.ankur.restaurantnearby.viewmodel.SplashViewModel
import dagger.android.support.DaggerAppCompatActivity
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

const val REQUEST_LOCATION_PERMISSION = 1001

/**
 * A Simple Subclass of [DaggerAppCompatActivity]
 * To show splash and check for location permission
 */
class SplashActivity : DaggerAppCompatActivity(),EasyPermissions.PermissionCallbacks {
    @Inject
    lateinit var viewModelProvideFactory: ViewModelProvideFactory
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initObject()
        observeViewModel()
    }

    private fun observeViewModel() {
        splashViewModel.start()
        splashViewModel.navigation.observe(this, Observer { event ->
            event?.let {
                when (it) {
                    is SplashEvent.GoToHome -> {
                        checkPermission()
                    }
                    is SplashEvent.ShowError -> {

                    }
                }
            }
        })
    }

    private fun initObject() {
        splashViewModel = ViewModelProvider(this, viewModelProvideFactory)
            .get(SplashViewModel::class.java)
    }

    /**
     * Check if it's required permission or not
     */
    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions()
        } else {
            startNextActivity()
        }
    }

    private fun requestPermissions() {
        if(EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, getString(R.string.permission_already_granted) , Toast.LENGTH_SHORT).show()
            startNextActivity()
        }
        else {
            EasyPermissions.requestPermissions(this, getString(R.string.text_location_permission), REQUEST_LOCATION_PERMISSION, Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun startNextActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(this, getString(R.string.permission_denied) , Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(this, getString(R.string.permission_already_granted) , Toast.LENGTH_SHORT).show()
        startNextActivity()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

}