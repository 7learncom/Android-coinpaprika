package com.mhd.coinpaprika

import com.mhd.coinpaprika.data.CoinRepositoryImpl
import com.mhd.coinpaprika.fake.FakeApiService
import com.mhd.coinpaprika.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CoinRepositoryTest {

    @Test
    fun `get coins verify coins list`() = runTest {
        val repository = CoinRepositoryImpl(FakeApiService())
        assertEquals(FakeDataSource.coinList, repository.getCoins())
    }

}