package com.mhd.coinpaprika.fake

import com.mhd.coinpaprika.data.CoinRepository
import com.mhd.coinpaprika.data.network.response.CoinDetailResponse
import com.mhd.coinpaprika.data.network.response.CoinsResponse

class FakeRepository : CoinRepository {
    override suspend fun getCoins(): List<CoinsResponse> {
        return FakeDataSource.coinList
    }

    override suspend fun getCoinDetail(id: String): CoinDetailResponse {
        TODO("Not yet implemented")
    }
}