package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class Link(
    val label: String,
    val url: String,
    val description: String? = null,
    val icon: String? = null
)

@Serializable
data class Links(
    val items: List<Link>
)

