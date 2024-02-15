package com.foss.core.models

data class UserModel(
    val password: String? = null,
    val email: String?,
    val full_name: String?,
    val phoneNumber: String?,
    val profileSignedUrl: String?,
    val refreshToken: String?,
    val token: String?
)