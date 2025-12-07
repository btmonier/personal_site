package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class NotFoundImages(
    val images: List<String>
)

