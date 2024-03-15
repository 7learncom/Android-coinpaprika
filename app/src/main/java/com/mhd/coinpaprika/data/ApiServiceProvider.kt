package com.mhd.coinpaprika.data

object ApiServiceProvider {

    val apiService: ApiService by lazy { ApiService.create() }

}