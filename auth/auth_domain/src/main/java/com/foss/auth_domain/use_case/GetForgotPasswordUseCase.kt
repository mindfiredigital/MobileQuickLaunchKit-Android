package com.foss.auth_domain.use_case

import com.foss.auth_domain.models.ForgotPasswordRequestParams
import com.foss.core.models.Resource
import com.foss.auth_domain.repository.AuthRepository
import com.foss.core.models.CustomResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetForgotPasswordUseCase @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke(forgotPasswordRequestParams: ForgotPasswordRequestParams): Flow<Resource<CustomResponseModel<Any?>>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        data = authRepository.forgotPassword(
                            forgotPasswordRequestParams
                        )
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = e.toString()))
            }
        }

}
