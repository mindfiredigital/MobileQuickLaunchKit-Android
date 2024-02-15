package com.foss.auth_domain.use_case

import com.foss.core.models.Resource
import com.foss.auth_domain.repository.AuthRepository
import com.foss.core.models.CustomResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetOtpVerificationUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(code: String): Flow<Resource<CustomResponseModel<Any?>>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        data = authRepository.verifyOpt(
                            code
                        )
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = e.toString()))
            }
        }

}