package com.mhd.coinpaprika.fake

import com.mhd.coinpaprika.data.network.ApiService
import com.mhd.coinpaprika.data.network.response.CoinDetailResponse
import com.mhd.coinpaprika.data.network.response.CoinsResponse

class FakeApiService : ApiService {
    override suspend fun getCoins(): List<CoinsResponse> {
        return FakeDataSource.coinList
    }

    override suspend fun getCoinById(id: String): CoinDetailResponse {
        TODO("Not yet implemented")
    }
}