package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class Presentation(
    val title: String,
    val authors: List<String>,
    val venue: String,
    val year: Int,
    val type: String // "poster" or "talk"
)

@Serializable
data class Presentations(
    val items: List<Presentation>
)

