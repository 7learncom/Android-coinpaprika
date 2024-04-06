package com.mhd.coinpaprika.detail

import com.mhd.coinpaprika.fake.FakeDataSource
import com.mhd.coinpaprika.fake.FakeRepository
import com.mhd.coinpaprika.rule.TestDispatcherRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CoinDetailViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun `get coin detail verify ui state success`() {
        val viewModel = CoinDetailViewModel(FakeRepository())
        viewModel.fetchCoinData("")
        val fakeDetail = FakeDataSource.coinDetail
        val expected = CoinDetailUiState.CoinData(
            name = fakeDetail.name,
            symbol = fakeDetail.symbol,
            description = fakeDetail.description,
            logo = fakeDetail.logo,
            isActive = fakeDetail.isActive,
            teamMembers = fakeDetail.team.map { it.name }
        )
        assertEquals(expected, viewModel.uiState.value)
    }

}