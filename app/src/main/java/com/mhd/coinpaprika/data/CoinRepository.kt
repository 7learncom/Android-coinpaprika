package com.mhd.coinpaprika.data

import com.mhd.coinpaprika.data.network.response.CoinDetailResponse
import com.mhd.coinpaprika.data.network.response.CoinsResponse
import com.mhd.coinpaprika.data.network.ApiService

interface CoinRepository {
    suspend fun getCoins(): List<CoinsResponse>
    suspend fun getCoinDetail(id: String): CoinDetailResponse
}

class CoinRepositoryImpl(private val apiService: ApiService) : CoinRepository {
    override suspend fun getCoins(): List<CoinsResponse> {
        return apiService.getCoins()
    }

    override suspend fun getCoinDetail(id: String): CoinDetailResponse {
        return apiService.getCoinById(id)
    }

}