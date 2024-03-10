package com.mhd.coinpaprika.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhd.coinpaprika.data.ApiService
import com.mhd.coinpaprika.data.model.response.CoinsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CoinListUiState>(CoinListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchCoins()
    }

    fun fetchCoins() {
        viewModelScope.launch {
            val apiService = ApiService.create()
            val coins = apiService.getCoins()
            _uiState.value = CoinListUiState.Success(coins)
        }
    }

}

sealed interface CoinListUiState {
    data object Loading : CoinListUiState
    data class Success(val coins: List<CoinsResponse>) : CoinListUiState
}