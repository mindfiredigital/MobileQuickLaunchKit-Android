package com.foss.core.use_cases

import com.foss.core.models.UserModel
import com.foss.core.network.MFMKDataStore
import javax.inject.Inject

class GetUserDataFromDataStoreUseCase @Inject constructor(private val mfmkDataStore: MFMKDataStore) {

    suspend operator fun invoke(): UserModel? {
        return mfmkDataStore.getUserData()
    }
//
//    suspend operator fun invoke(): UserModel? {
//        return mfmkDataStore.getUserData()
//    }


}