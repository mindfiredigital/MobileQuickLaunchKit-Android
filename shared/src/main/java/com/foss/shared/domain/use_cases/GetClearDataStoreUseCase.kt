package com.foss.shared.domain.use_cases

import com.foss.shared.domain.repository.SharedDataStore
import javax.inject.Inject

class GetClearDataStoreUseCase @Inject constructor(private val dataStore: SharedDataStore) {

    suspend operator fun invoke() {
        dataStore.clearDataStore()
    }

}