package com.foss.core.use_cases

import com.foss.core.models.UserModel
import com.foss.core.network.MFMKDataStore
import javax.inject.Inject


class SetUserDataToDataStoreUseCase @Inject constructor(private val mfmkDataStore: MFMKDataStore) {

    suspend operator fun invoke(userModel: UserModel) {
        mfmkDataStore.saveUserData(userModel)
    }

}