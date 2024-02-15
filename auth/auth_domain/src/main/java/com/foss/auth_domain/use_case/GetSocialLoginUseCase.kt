package com.foss.auth_domain.use_case

import com.foss.auth_domain.models.SocialLoginRequestParams
import com.foss.auth_domain.repository.AuthRepository
import com.foss.core.models.CustomResponseModel
import com.foss.core.models.Resource
import com.foss.shared.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSocialLoginUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(socialLoginRequestParams: SocialLoginRequestParams): Flow<Resource<CustomResponseModel<UserModel?>>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        data = authRepository.socialLogin(
                            socialLoginRequestParams = socialLoginRequestParams
                        )
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = e.toString()))
            }
        }

}