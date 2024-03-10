package com.mhd.coinpaprika.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mhd.coinpaprika.R
import com.mhd.coinpaprika.data.model.response.CoinsResponse
import com.mhd.coinpaprika.databinding.FragmentListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CoinListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!

    private val viewModel: CoinListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
        observeUiState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {

                    when (it) {
                        CoinListUiState.Loading -> binding.progressCircular.isVisible = true
                        is CoinListUiState.Success -> setUpRecyclerView(coins = it.coins)
                    }

                }
            }
        }
    }

    private fun setUpRecyclerView(coins: List<CoinsResponse>) {
        binding.progressCircular.isVisible = false
        val adapter = CoinAdapter(coins) {

        }

        binding.rvCoins.adapter = adapter
    }
}