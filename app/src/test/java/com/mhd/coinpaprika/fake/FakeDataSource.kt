package com.mhd.coinpaprika.fake

import com.mhd.coinpaprika.data.network.response.CoinDetailResponse
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

    val coinDetail = CoinDetailResponse(
        id = "btc-bitcoin",
        name = "Bitcoin",
        symbol = "Btc",
        rank = 1,
        isNew = false,
        isActive = true,
        type = "Coin",
        logo = "logo",
        tags = listOf(CoinDetailResponse.Tag(id = "", name = "", coinCounter = 1, icoCounter = 0)),
        team = listOf(CoinDetailResponse.Team(id = "", name = "Satoshi", position = "Author")),
        description = "Description",
        message = "Message",
        openSource = true,
        hardwareWallet = true,
        startedAt = "",
        developmentStatus = "",
        proofType = "",
        orgStructure = "",
        hashAlgorithm = "",
        links = CoinDetailResponse.Links(
            explorer = listOf(),
            facebook = listOf(),
            reddit = listOf(),
            sourceCode = listOf(),
            website = listOf(),
            youtube = listOf(),
        ),
        linksExtended = listOf(),
        whitepaper = CoinDetailResponse.Whitepaper(link = "", thumbnail = ""),
        firstDataAt = "",
        lastDataAt = ""
    )
}