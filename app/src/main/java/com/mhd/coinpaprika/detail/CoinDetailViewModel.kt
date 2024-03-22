package com.mhd.coinpaprika.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhd.coinpaprika.data.ApiService
import com.mhd.coinpaprika.data.ApiServiceProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CoinDetailUiState>(CoinDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()
    fun fetchCoinData(coinId: String) {
        viewModelScope.launch {
            val apiService = ApiServiceProvider.apiService
            val response = apiService.getCoinById(coinId)
            val state = CoinDetailUiState.CoinData(
                name = response.name,
                symbol = response.symbol,
                description = response.description,
                logo = response.logo,
                isActive = response.isActive,
                teamMembers = response.team.map { it.name }
            )
            _uiState.value = state
        }
    }

}

sealed interface CoinDetailUiState {
    data object Loading : CoinDetailUiState
    data class CoinData(
        val name: String,
        val symbol: String,
        val description: String,
        val logo: String,
        val isActive: Boolean,
        val teamMembers: List<String>,
    ) : CoinDetailUiState
}