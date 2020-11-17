package com.ankur.restaurantnearby.di

import com.ankur.restaurantnearby.ui.MainActivity
import com.ankur.restaurantnearby.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


// declare all the activity here , dependency of activity are provided by this module
@Module
abstract class ActivityBuilderModule {


    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class, ViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity

}