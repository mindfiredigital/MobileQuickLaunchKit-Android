package com.foss.shared.data.repository

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.clear
import androidx.datastore.preferences.edit
import com.foss.shared.common.PreferenceKeys
import com.foss.shared.data.model.UserModel
import com.foss.shared.domain.repository.SharedDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class SharedDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SharedDataStore {

    private val TAG = "TAG"

    override suspend fun saveUserData(userModel: UserModel) {
        try {
            dataStore.edit {
                it[PreferenceKeys.Keys.email] = userModel.email!!
                it[PreferenceKeys.Keys.password] = userModel.password!!
                it[PreferenceKeys.Keys.phoneNo] = userModel.phoneNumber!!
                it[PreferenceKeys.Keys.token] = userModel.token!!
                it[PreferenceKeys.Keys.fullName] = userModel.full_name!!
            }
        } catch (e: Exception) {
            Timber.tag(TAG).e("Error: ${e.message}")
        }

    }

    override suspend fun getUserData(): UserModel? {
        var user: UserModel? = null

        // Fetch preferences using DataStore
        val currentPreferences = dataStore.data.first()

        user = UserModel(
            email = currentPreferences[PreferenceKeys.Keys.email] ?: "",
            phoneNumber = currentPreferences[PreferenceKeys.Keys.phoneNo] ?: "",
            token = currentPreferences[PreferenceKeys.Keys.token] ?: "",
            full_name = currentPreferences[PreferenceKeys.Keys.fullName] ?: "",
            password = currentPreferences[PreferenceKeys.Keys.password] ?: "",
            refreshToken = null,
            profileSignedUrl = null
        )
        return user
    }

    override fun getTokenData(): Flow<String?> {
        return dataStore.data
            .map { currentPreferences ->
                currentPreferences[PreferenceKeys.Keys.token]
            }
    }

    override suspend fun clearDataStore() {
        dataStore.edit {
            it.clear()
        }
    }
}
