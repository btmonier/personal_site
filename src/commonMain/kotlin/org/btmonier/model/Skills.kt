package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class SkillsData(
    val categories: List<SkillCategory>,
    val mentoring: MentoringData
)

@Serializable
data class SkillCategory(
    val name: String,
    val icon: String,
    val items: List<String>
)

@Serializable
data class MentoringData(
    val summary: String,
    val certifications: List<String>,
    val mentees: List<Mentee>
)

@Serializable
data class Mentee(
    val name: String,
    val level: String,
    val duration: String,
    val institution: String,
    val year: String
)

