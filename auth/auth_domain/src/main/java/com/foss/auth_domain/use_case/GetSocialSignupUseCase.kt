package com.foss.auth_domain.use_case

import com.foss.core.models.Resource
import com.foss.auth_domain.models.SocialSignupRequestParams
import com.foss.auth_domain.repository.AuthRepository
import com.foss.core.models.CustomResponseModel
import com.foss.core.models.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSocialSignUpUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(socialSignupRequestParams: SocialSignupRequestParams): Flow<Resource<CustomResponseModel<UserModel?>>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        data = authRepository.socialSignup(
                            socialSignupRequestParams = socialSignupRequestParams
                        )
                    )
                )
            } catch (e: Exception) {
                emit(Resource.Error(message = e.toString()))
            }
        }

}