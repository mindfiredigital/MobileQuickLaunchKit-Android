package com.foss.settings.data.models

data class GetuserProfileResponseDTO(
    val `data`: Data?,
    val error: Any?,
    val message: String?,
    val status: String?
) {
    data class Data(
        val email: String?,
        val full_name: String?,
        val phone_number: String?,
        val profileSignedUrl: String?
    )
}