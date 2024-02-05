package com.foss.settings.data.repository

import com.foss.auth_data.common.toDomainModel
import com.foss.auth_data.source.remote.SettingApi
import com.foss.core.models.CustomResponseModel
import com.foss.core.models.UserModel
import com.foss.settings.domain.models.CreateUpdateProfileRequestParams
import com.foss.settings.domain.repository.SettingsRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.Date
import javax.inject.Inject

class SettingsRepositoryImplementation @Inject constructor(
    private val apiService: SettingApi,
) : SettingsRepository {
    override suspend fun createUpdateProfile(createUpdateProfileRequestParams: CreateUpdateProfileRequestParams): CustomResponseModel<Any?>? {
        try {
            return apiService.createUpdateProfile(createUpdateProfileRequestParams).toDomainModel()
        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }
    }

    override suspend fun getUserProfile(): CustomResponseModel<UserModel?>? {
        try {
            return apiService.getUserProfile().toDomainModel()
        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }
    }

    override suspend fun changePassword(): CustomResponseModel<Any?>? {
        TODO("Not yet implemented")
    }

    override suspend fun uploadProfileImage(request: ByteArray): CustomResponseModel<Any?>? {

        try {
            val requestBody = request.toRequestBody("image/*".toMediaTypeOrNull(), 0, request.size)
            val imagePart = MultipartBody.Part.createFormData(
                "image",
                "${Date()}.jpg",
                requestBody
            )
            return apiService.uploadImage(imagePart).toDomainModel()
        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }

    }
}