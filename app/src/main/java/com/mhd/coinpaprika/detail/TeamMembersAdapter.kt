package com.mhd.coinpaprika.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhd.coinpaprika.databinding.ItemCoinDetailTeamMemberBinding

class TeamMembersAdapter(
    private val names: List<String>,
) : RecyclerView.Adapter<TeamMembersAdapter.TeamMemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCoinDetailTeamMemberBinding.inflate(inflater, parent, false)
        return TeamMemberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
        holder.bind(name = names[position])
    }

    inner class TeamMemberViewHolder(
        private val binding: ItemCoinDetailTeamMemberBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(name: String) {
            binding.tvName.text = name
        }
    }
}