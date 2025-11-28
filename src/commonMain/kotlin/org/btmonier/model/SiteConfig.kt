package org.btmonier.model

import kotlinx.serialization.Serializable

@Serializable
data class SiteConfig(
    val name: String,
    val title: String,
    val subtitle: String,
    val email: String,
    val socialLinks: List<SocialLink>
)

@Serializable
data class SocialLink(
    val platform: String,
    val url: String,
    val icon: String
)

