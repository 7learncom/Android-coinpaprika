package com.mhd.coinpaprika.data.network.response


import com.google.gson.annotations.SerializedName

data class CoinDetailResponse(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("is_active")
    val isActive: Boolean,
    val type: String,
    val logo: String,
    val tags: List<Tag>,
    val team: List<Team>,
    val description: String,
    val message: String,
    @SerializedName("open_source")
    val openSource: Boolean,
    @SerializedName("started_at")
    val startedAt: String,
    @SerializedName("development_status")
    val developmentStatus: String,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerializedName("proof_type")
    val proofType: String,
    @SerializedName("org_structure")
    val orgStructure: String,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String,
    val links: Links,
    @SerializedName("links_extended")
    val linksExtended: List<LinksExtended>,
    val whitepaper: Whitepaper,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("last_data_at")
    val lastDataAt: String
) {
    data class Tag(
        val id: String,
        val name: String,
        @SerializedName("coin_counter")
        val coinCounter: Int,
        @SerializedName("ico_counter")
        val icoCounter: Int
    )

    data class Team(
        val id: String,
        val name: String,
        val position: String
    )

    data class Links(
        val explorer: List<String>,
        val facebook: List<String>,
        val reddit: List<String>,
        @SerializedName("source_code")
        val sourceCode: List<String>,
        val website: List<String>,
        val youtube: List<String>
    )

    data class LinksExtended(
        val url: String,
        val type: String,
        val stats: Stats
    ) {
        data class Stats(
            val subscribers: Int,
            val contributors: Int,
            val stars: Int,
            val followers: Int
        )
    }

    data class Whitepaper(
        val link: String,
        val thumbnail: String
    )
}