package com.mhd.coinpaprika.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhd.coinpaprika.R
import com.mhd.coinpaprika.data.model.response.CoinsResponse
import com.mhd.coinpaprika.databinding.ItemCoinBinding

class CoinAdapter(
    private val coins: List<CoinsResponse>,
    private val onCoinClick: (String) -> Unit,
) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCoinBinding.inflate(inflater, parent, false)
        return CoinViewHolder(binding, onCoinClick)
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position])
    }

    inner class CoinViewHolder(
        private val binding: ItemCoinBinding,
        onCoinClick: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: CoinsResponse) {
            binding.tvRank.text = coin.rank.toString()
            binding.tvNameSymbol.text =
                binding.root.context.getString(R.string.coinItem_nameSymbol, coin.name, coin.symbol)
            binding.viewActive.setBackgroundResource(if (coin.isActive) R.drawable.ic_active else R.drawable.ic_inactive)

            binding.root.setOnClickListener {
                onCoinClick(coin.id)
            }
        }


    }
}