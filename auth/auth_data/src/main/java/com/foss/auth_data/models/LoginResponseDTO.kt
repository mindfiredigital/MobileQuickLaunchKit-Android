package com.foss.auth_data.models

data class LoginResponseDTO(
    val `data`: UserModelDTO?,
    val error: Any?,
    val message: String?,
    val status: String?
)