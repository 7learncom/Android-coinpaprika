package com.mhd.coinpaprika.data.network

import com.mhd.coinpaprika.data.network.response.CoinDetailResponse
import com.mhd.coinpaprika.data.network.response.CoinsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("coins")
    suspend fun getCoins(): List<CoinsResponse>

    @GET("coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): CoinDetailResponse

}