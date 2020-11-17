package com.ankur.restaurantnearby.util

sealed class SplashEvent {
    object GoToHome : SplashEvent()
    object ShowError : SplashEvent()
}