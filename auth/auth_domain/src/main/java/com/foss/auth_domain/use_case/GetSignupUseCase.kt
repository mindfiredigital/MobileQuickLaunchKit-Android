package com.foss.auth_domain.use_case

import com.foss.core.models.Resource
import com.foss.auth_domain.models.SignUpRequestParamsModel
import com.foss.auth_domain.repository.AuthRepository
import com.foss.shared.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSignUpUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(signUpRequestParamsModel: SignUpRequestParamsModel): Flow<Resource<UserModel>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        data = authRepository.signup(
                            signUpRequestParamsModel = signUpRequestParamsModel
                        )
                    )
                )
            } catch (e: java.lang.Exception) {
                emit(Resource.Error(message = e.toString()))
            }
        }

}