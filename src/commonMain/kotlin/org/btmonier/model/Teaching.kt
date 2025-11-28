package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class Teaching(
    val title: String,
    val institution: String,
    val period: String,
    val description: String? = null,
    val url: String? = null,
    val participants: Int? = null
)

@Serializable
data class TeachingList(
    val items: List<Teaching>
)

