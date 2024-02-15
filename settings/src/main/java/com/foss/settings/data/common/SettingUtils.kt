package com.foss.settings.data.common


import com.foss.core.models.CustomResponseModel
import com.foss.settings.data.models.CreateUpdateProfileResponseDTO
import com.foss.settings.data.models.GetuserProfileResponseDTO
import com.foss.settings.data.models.UploadProfileImageResponseDTO
import com.foss.shared.data.model.UserModel

fun CreateUpdateProfileResponseDTO.toDomainModel(): CustomResponseModel<Any?> {
    return CustomResponseModel(message = this.message, data = null);

}

fun UploadProfileImageResponseDTO.toDomainModel(): CustomResponseModel<Any?> {
    return CustomResponseModel(message = this.message, data = this.data?.profileSignedUrl);

}

fun GetuserProfileResponseDTO.toDomainModel(): CustomResponseModel<UserModel?>? {
    return CustomResponseModel(
        message = this.message,
        data = UserModel(
            email = this.data?.email,
            full_name = this.data?.full_name,
            password = null,
            refreshToken = null,
            token = null,
            phoneNumber = this.data?.phone_number,
            profileSignedUrl = this.data?.profileSignedUrl,
        ),
    )
}


