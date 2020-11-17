package com.ankur.restaurantnearby.di

import androidx.lifecycle.ViewModel
import com.ankur.restaurantnearby.viewmodel.NearbyPlacesViewModel
import com.ankur.restaurantnearby.viewmodel.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NearbyPlacesViewModel::class)
    abstract fun bindPlacesViewModel(nearbyPlacesViewModel: NearbyPlacesViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}