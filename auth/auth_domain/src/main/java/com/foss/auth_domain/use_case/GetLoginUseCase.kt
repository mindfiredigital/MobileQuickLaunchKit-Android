package com.foss.auth_domain.use_case

import com.foss.auth_domain.models.LoginRequestParamsModel
import com.foss.auth_domain.repository.AuthRepository
import com.foss.core.models.Resource
import com.foss.shared.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLoginUseCase(private val authRepository: AuthRepository) {

    operator fun invoke(loginRequestParamsMode: LoginRequestParamsModel): Flow<Resource<UserModel>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        data = authRepository.login(
                            loginRequestParamsMode = loginRequestParamsMode
                        )
                    )
                )
            } catch (e: java.net.ConnectException) {
                emit(Resource.Error(message = e.toString()))
            } catch (e: java.lang.Exception) {
                emit(Resource.Error(message = e.toString()))
            }
        }

}
