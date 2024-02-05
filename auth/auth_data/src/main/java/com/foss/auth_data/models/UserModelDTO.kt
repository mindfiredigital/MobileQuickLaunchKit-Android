package com.foss.auth_data.models

data class UserModelDTO(
    val access_token: String?,
    val email: String?,
    val full_name: String?,
    val phone_number: String?,
    val profileSignedUrl: String?,
    val refresh_token: String?,
    val token: String?
)