package com.foss.shared.domain.use_cases

import com.foss.shared.data.model.UserModel
import com.foss.shared.domain.repository.SharedDataStore
import javax.inject.Inject

class GetUserDataFromDataStoreUseCase @Inject constructor(private val dataStore: SharedDataStore) {

    suspend operator fun invoke(): UserModel? {
        return dataStore.getUserData()
    }
//
//    suspend operator fun invoke(): UserModel? {
//        return mfmkDataStore.getUserData()
//    }


}