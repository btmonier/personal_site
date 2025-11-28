package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class ScholarStats(
    val lastUpdated: String,
    val citations: StatPair,
    val hIndex: StatPair,
    val i10Index: StatPair,
    val citationsByYear: List<YearCount>
)

@Serializable
data class StatPair(
    val all: Int,
    val since2020: Int
)

@Serializable
data class YearCount(
    val year: Int,
    val count: Int
)

