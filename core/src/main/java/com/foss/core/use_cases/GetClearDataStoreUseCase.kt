package com.foss.core.use_cases

import com.foss.core.network.MFMKDataStore
import javax.inject.Inject

class GetClearDataStoreUseCase @Inject constructor(private val mfmkDataStore: MFMKDataStore) {

    suspend operator fun invoke() {
        mfmkDataStore.clearDataStore()
    }

}