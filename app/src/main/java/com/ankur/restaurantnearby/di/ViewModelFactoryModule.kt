package com.ankur.restaurantnearby.di

import androidx.lifecycle.ViewModelProvider
import com.ankur.restaurantnearby.util.ViewModelProvideFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule{

    @Binds
    abstract fun bindViewModelFactory(viewModelProvideFactory: ViewModelProvideFactory): ViewModelProvider.Factory
}