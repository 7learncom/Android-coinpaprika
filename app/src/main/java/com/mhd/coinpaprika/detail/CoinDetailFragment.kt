package com.mhd.coinpaprika.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import com.mhd.coinpaprika.R
import com.mhd.coinpaprika.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch

class CoinDetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!

    private val viewModel: CoinDetailViewModel by viewModels(factoryProducer = { CoinDetailViewModel.factory })
    private val args: CoinDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        observeUiState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchCoinData(args.coinId)
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    binding.progressCircular.isVisible = it is CoinDetailUiState.Loading
                    binding.ivConnectionError.isVisible =
                        it is CoinDetailUiState.NetworkError || it is CoinDetailUiState.NotFoundError

                    if (it is CoinDetailUiState.NotFoundError) {
                        binding.tvError.isVisible = true
                        binding.tvError.text = it.message
                    } else {
                        binding.tvError.isVisible = false
                    }

                    if (it is CoinDetailUiState.CoinData) {
                        showCoinDetail(
                            name = it.name,
                            symbol = it.symbol,
                            description = it.description,
                            logo = it.logo,
                            isActive = it.isActive,
                            teamMembers = it.teamMembers
                        )

                    }
                }
            }

        }
    }

    private fun showCoinDetail(
        name: String,
        symbol: String,
        description: String,
        logo: String,
        isActive: Boolean,
        teamMembers: List<String>,
    ) {

        binding.ivLogo.load(logo)
        binding.tvName.text = getString(R.string.coinDetail_name, name)
        binding.tvSymbol.text = getString(R.string.coinDetail_symbol, symbol)
        binding.tvDescription.text = description
        binding.viewActive.setBackgroundResource(if (isActive) R.drawable.ic_active else R.drawable.ic_inactive)

        binding.rvTeamMembers.adapter = TeamMembersAdapter(teamMembers)
    }
}