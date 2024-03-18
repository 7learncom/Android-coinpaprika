package com.mhd.coinpaprika.data

import com.mhd.coinpaprika.data.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppContainer {
    val coinRepository: CoinRepository
}

class DefaultAppContainer : AppContainer {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.coinpaprika.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService by lazy {
        retrofit.create()
    }

    override val coinRepository: CoinRepository by lazy { CoinRepositoryImpl(apiService) }

}