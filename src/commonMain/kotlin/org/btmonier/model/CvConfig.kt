package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class CvConfig(
    val institution: String,
    val address: String,
    val phone: String,
    val website: String,
    val cvDate: String
)

