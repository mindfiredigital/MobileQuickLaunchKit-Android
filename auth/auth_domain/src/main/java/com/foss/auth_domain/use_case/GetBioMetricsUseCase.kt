package com.foss.auth_domain.use_case

import androidx.fragment.app.FragmentActivity
import com.foss.core.models.Resource
import com.foss.auth_domain.repository.AuthRepository
import com.foss.core.models.CustomResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetBioMetricsUseCase @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke(context: FragmentActivity): Flow<Resource<CustomResponseModel<Any?>>> =
        flow {
            emit(Resource.Loading())
            try {

                val (success, message) = authRepository.authenticateWithBiometric(context)
                if (success) {
                    emit(
                        Resource.Success(
                            data = CustomResponseModel(data = true, message = message)
                        )
                    )
                } else {
                    emit(Resource.Error(message))
                }

            } catch (e: Exception) {
                emit(Resource.Error(message = e.toString()))
            }
        }

}