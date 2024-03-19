package com.mhd.coinpaprika.fake

import com.mhd.coinpaprika.data.network.response.CoinsResponse

object FakeDataSource {
    val coinList = listOf(
        CoinsResponse(
            id = "Btc-bitcoin",
            name = "Bitcoin",
            symbol = "BTC",
            rank = 1,
            isNew = false,
            isActive = true,
            type = "Coin"
        )
    )
}