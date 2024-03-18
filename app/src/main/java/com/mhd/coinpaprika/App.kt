package com.mhd.coinpaprika

import android.app.Application
import com.mhd.coinpaprika.data.AppContainer
import com.mhd.coinpaprika.data.DefaultAppContainer

class App: Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
    }

}