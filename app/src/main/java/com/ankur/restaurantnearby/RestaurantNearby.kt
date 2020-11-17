package com.ankur.restaurantnearby

import com.ankur.restaurantnearby.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class RestaurantNearby : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}