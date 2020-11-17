package com.ankur.restaurantnearby.di

import com.ankur.restaurantnearby.ui.RestaurantListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeRestaurantList(): RestaurantListFragment
}