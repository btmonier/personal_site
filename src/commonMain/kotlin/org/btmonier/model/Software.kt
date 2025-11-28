package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class Software(
    val name: String,
    val description: String,
    val languages: List<String> = emptyList(),
    val repoUrl: String? = null,
    val docsUrl: String? = null,
    val tags: List<String> = emptyList()
)

@Serializable
data class SoftwareList(
    val items: List<Software>
)

