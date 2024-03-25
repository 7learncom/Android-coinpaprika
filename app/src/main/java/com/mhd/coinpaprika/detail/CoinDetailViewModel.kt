package com.mhd.coinpaprika.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.gson.Gson
import com.mhd.coinpaprika.App
import com.mhd.coinpaprika.data.CoinRepository
import com.mhd.coinpaprika.data.network.response.ErrorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
            try {
                val response = repository.getCoinDetail("errrrrrrrrrrrr")
                val state = CoinDetailUiState.CoinData(
                    name = response.name,
                    symbol = response.symbol,
                    description = response.description,
                    logo = response.logo,
                    isActive = response.isActive,
                    teamMembers = response.team.map { it.name }
                )
                _uiState.value = state
            } catch (exception: Exception) {

                if (exception is HttpException && exception.code() == 404) {
                    val errorBody = exception.response()!!.errorBody()!!.string()
                    val errorResponse: ErrorResponse =
                        Gson().fromJson(errorBody, ErrorResponse::class.java)
                    _uiState.value = CoinDetailUiState.NotFoundError(errorResponse.error)
                } else {
                    _uiState.value = CoinDetailUiState.NetworkError
                }

            }
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

    data class NotFoundError(val message: String) : CoinDetailUiState
    data object NetworkError : CoinDetailUiState
}