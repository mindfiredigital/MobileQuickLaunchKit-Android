package com.foss.settings.domain.repository

import com.foss.core.models.CustomResponseModel
import com.foss.core.models.UserModel
import com.foss.settings.domain.models.CreateUpdateProfileRequestParams


interface SettingsRepository {

    suspend fun createUpdateProfile(createUpdateProfileRequestParams: CreateUpdateProfileRequestParams): CustomResponseModel<Any?>?

    suspend fun getUserProfile(): CustomResponseModel<UserModel?>?

    suspend fun changePassword(): CustomResponseModel<Any?>?

    suspend fun uploadProfileImage(request: ByteArray): CustomResponseModel<Any?>?


}