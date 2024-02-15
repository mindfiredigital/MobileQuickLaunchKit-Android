package com.foss.settings.domain.use_cases

import com.foss.core.models.CustomResponseModel
import com.foss.core.models.Resource
import com.foss.settings.domain.models.CreateUpdateProfileRequestParams
import com.foss.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCreateUpdateProfileUseCase(private val settingsRepository: SettingsRepository) {

    operator fun invoke(createUpdateProfileRequestParams: CreateUpdateProfileRequestParams): Flow<Resource<CustomResponseModel<Any?>>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        data = settingsRepository.createUpdateProfile(
                            createUpdateProfileRequestParams
                        )
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = e.toString()))
            }
        }

}
