package com.foss.shared.domain.repository

import com.foss.shared.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface SharedDataStore {

    suspend fun saveUserData(userModel: UserModel)

    suspend fun getUserData(): UserModel?

    fun getTokenData(): Flow<String?>

    suspend fun clearDataStore()

}