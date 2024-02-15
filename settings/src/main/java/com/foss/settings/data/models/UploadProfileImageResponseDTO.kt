package com.foss.settings.data.models

data class UploadProfileImageResponseDTO(
    val `data`: Data?,
    val error: Any?,
    val message: String?,
    val status: String?
) {
    data class Data(
        val profileSignedUrl: String?
    )
}