package com.ankur.restaurantnearby.di

import com.ankur.restaurantnearby.data.repo.PlacesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRepository():PlacesRepository{
        return PlacesRepository()
    }
}