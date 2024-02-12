package com.foss.settings.domain.use_cases

import com.foss.core.models.CustomResponseModel
import com.foss.core.models.Resource
import com.foss.settings.domain.repository.SettingsRepository
import com.foss.shared.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFetchUserProfileUseCase(private val settingsRepository: SettingsRepository) {

    operator fun invoke(): Flow<Resource<CustomResponseModel<UserModel?>>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        data = settingsRepository.getUserProfile(
                        )
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = e.toString()))
            }
        }

}