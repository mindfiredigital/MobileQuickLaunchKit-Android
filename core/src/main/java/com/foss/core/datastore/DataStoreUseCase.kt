package com.foss.core.datastore.use_cases

import com.foss.core.datastore.MFMKDataStore
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @Description:
 *
 *
 * @Author: Abhishek Kumar
 * Created On: 09-02-2024
 */
class DataStoreUseCase @Inject constructor(private val dataStore: MFMKDataStore) {

    suspend fun saveData(key: String, value: Any) {
        try {
            // Save data to the data store
            dataStore.saveData(key, value)
        } catch (e: Exception) {
            // Handle the exception
            handleError("Error saving data for key $key", e)
        }
    }

    suspend fun getData(key: String, defaultValue: Any): Any {
        return try {
            // Retrieve data from the data store
            dataStore.getData(key, defaultValue)
        } catch (e: Exception) {
            // Handle the exception
            handleError("Error getting data for key $key", e)
            defaultValue
        }
    }

    fun getDataFlow(key: String, defaultValue: Any): Flow<Any> {
        return dataStore.getDataFlow(key, defaultValue)
    }

    suspend fun clearData(key: String) {
        try {
            // Clear data associated with the specified key
            dataStore.clearData(key)
        } catch (e: Exception) {
            // Handle the exception
            handleError("Error clearing data for key $key", e)
        }
    }

    suspend fun <R> saveArray(key: String, value: Array<R>) {
        try {
            // Save array to the data store
            dataStore.saveArray(key, value)
        } catch (e: Exception) {
            // Handle the exception
            handleError("Error saving array for key $key", e)
        }
    }

    suspend fun <R> getArray(key: String, defaultValue: Array<R>): Array<R> {
        return try {
            // Retrieve array from the data store
            dataStore.getArray(key, defaultValue)
        } catch (e: Exception) {
            // Handle the exception
            handleError("Error getting array for key $key", e)
            defaultValue
        }
    }

    suspend fun <R> saveObject(key: String, value: R) {
        try {
            // Save object to the data store
            dataStore.saveObject(key, value)
        } catch (e: Exception) {
            // Handle the exception
            handleError("Error saving object for key $key", e)
        }
    }

    suspend fun <R> getObject(key: String, defaultValue: R): R {
        return try {
            // Retrieve object from the data store
            dataStore.getObject(key, defaultValue)
        } catch (e: Exception) {
            // Handle the exception
            handleError("Error getting object for key $key", e)
            defaultValue
        }
    }

    fun <R> getObjectFlow(key: String, clazz: Class<R>, defaultValue: R): Flow<R> {
        return dataStore.getObjectFlow(key, clazz, defaultValue)
    }

    private fun handleError(message: String, exception: Exception) {
        // Log or handle the exception as needed
        Timber.e(exception, message)
    }
}
