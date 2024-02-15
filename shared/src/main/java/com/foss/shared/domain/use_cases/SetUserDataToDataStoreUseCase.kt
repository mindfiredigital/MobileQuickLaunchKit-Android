package com.foss.shared.domain.use_cases

import com.foss.shared.data.model.UserModel
import com.foss.shared.domain.repository.SharedDataStore
import javax.inject.Inject


class SetUserDataToDataStoreUseCase @Inject constructor(private val dataStore: SharedDataStore) {

    suspend operator fun invoke(userModel: UserModel) {
        dataStore.saveUserData(userModel)
    }

}