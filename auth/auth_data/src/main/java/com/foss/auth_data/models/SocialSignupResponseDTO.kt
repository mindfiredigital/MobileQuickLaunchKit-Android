package com.foss.auth_data.models

data class SocialSignupResponseDTO(
    val `data`: UserModelDTO?,
    val error: Any?,
    val message: String?,
    val status: String?
)

