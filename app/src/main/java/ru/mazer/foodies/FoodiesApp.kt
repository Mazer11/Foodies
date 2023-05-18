package ru.mazer.foodies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class FoodiesApp: Application(){

    open fun getBaseUrl() = "https://api.testovoe.com"

}