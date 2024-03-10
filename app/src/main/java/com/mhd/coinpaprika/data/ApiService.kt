package com.mhd.coinpaprika.data

import com.mhd.coinpaprika.data.model.response.CoinsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface ApiService {

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.coinpaprika.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create()
        }
    }

    @GET("coins")
    suspend fun getCoins(): List<CoinsResponse>

}