package com.mhd.coinpaprika.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mhd.coinpaprika.App
import com.mhd.coinpaprika.data.CoinRepository
import com.mhd.coinpaprika.data.network.response.CoinsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinListViewModel(
    private val repository: CoinRepository
) : ViewModel() {

    companion object{
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App
                val container = application.appContainer
                val repository = container.coinRepository
                CoinListViewModel(repository)
            }
        }
    }

    private val _uiState = MutableStateFlow<CoinListUiState>(CoinListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchCoins()
    }

    fun fetchCoins() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val coins = repository.getCoins()
                    _uiState.value = CoinListUiState.Success(coins)
                } catch (e: Exception){
                    _uiState.value = CoinListUiState.Error
                }
            }
        }
    }

}

sealed interface CoinListUiState {
    data object Loading : CoinListUiState
    data class Success(val coins: List<CoinsResponse>) : CoinListUiState
    data object Error : CoinListUiState
}