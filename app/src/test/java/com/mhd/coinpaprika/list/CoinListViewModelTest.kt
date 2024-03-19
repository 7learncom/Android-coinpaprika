package com.mhd.coinpaprika.list

import com.mhd.coinpaprika.fake.FakeApiService
import com.mhd.coinpaprika.fake.FakeDataSource
import com.mhd.coinpaprika.fake.FakeRepository
import com.mhd.coinpaprika.rule.TestDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CoinListViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun `get coin list verify ui state success`() = runTest {
        val viewModel = CoinListViewModel(repository = FakeRepository())
        assertEquals(CoinListUiState.Success(FakeDataSource.coinList), viewModel.uiState.value)
    }

}