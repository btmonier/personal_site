package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class Degree(
    val degree: String,
    val field: String,
    val institution: String,
    val department: String? = null,
    val location: String,
    val year: Int,
    val thesis: String? = null,
    val thesisUrl: String? = null
)

@Serializable
data class Experience(
    val position: String,
    val institution: String,
    val location: String,
    val period: String,
    val advisor: String? = null,
    val highlights: List<String> = emptyList()
)

@Serializable
data class EducationData(
    val degrees: List<Degree>,
    val experience: List<Experience>
)

