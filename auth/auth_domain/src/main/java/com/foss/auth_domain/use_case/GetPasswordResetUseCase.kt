package com.foss.auth_domain.use_case

import com.foss.auth_domain.models.PasswordResetRequestParams
import com.foss.core.models.Resource
import com.foss.auth_domain.repository.AuthRepository
import com.foss.core.models.CustomResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetPasswordResetUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(passwordResetRequestParams: PasswordResetRequestParams): Flow<Resource<CustomResponseModel<Any?>>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        data = authRepository.resetPassword(
                            passwordResetRequestParams
                        )
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = e.toString()))
            }
        }

}