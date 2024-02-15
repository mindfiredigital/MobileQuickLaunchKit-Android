package com.foss.auth_data.models

data class PasswordResetResponseDTO(
    val `data`: String?,
    val error: Any?,
    val message: String?,
    val status: String?
)