package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class About(
    val bio: String,
    val profileImage: String? = null,
    val highlights: List<String> = emptyList()
)

