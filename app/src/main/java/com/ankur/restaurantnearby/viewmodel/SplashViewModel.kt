package com.ankur.restaurantnearby.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ankur.restaurantnearby.util.SplashEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {
    private val splashDuration = 1000L
    val navigation = MutableLiveData<SplashEvent>()
    fun start(){
        CoroutineScope(Dispatchers.IO).launch {
            delay(splashDuration)
            navigation.postValue(SplashEvent.GoToHome)
        }
    }
}