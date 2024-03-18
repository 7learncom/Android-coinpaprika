package com.mhd.coinpaprika.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mhd.coinpaprika.App
import com.mhd.coinpaprika.data.CoinRepository
import com.mhd.coinpaprika.data.network.ApiService
import com.mhd.coinpaprika.list.CoinListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinDetailViewModel(
    private val repository: CoinRepository,
) : ViewModel() {

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App
                val container = application.appContainer
                val repository = container.coinRepository
                CoinDetailViewModel(repository)
            }
        }
    }

    private val _uiState = MutableStateFlow<CoinDetailUiState>(CoinDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()
    fun fetchCoinData(coinId: String) {
        viewModelScope.launch {
            val response = repository.getCoinDetail(coinId)
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