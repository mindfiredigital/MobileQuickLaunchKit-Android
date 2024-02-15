package com.foss.core.network

import com.foss.core.models.UserModel
import kotlinx.coroutines.flow.Flow

interface MFMKDataStore {

    suspend fun saveUserData(userModel: UserModel)

    suspend fun getUserData(): UserModel?

    fun getTokenData(): Flow<String?>

    suspend fun clearDataStore()
}



