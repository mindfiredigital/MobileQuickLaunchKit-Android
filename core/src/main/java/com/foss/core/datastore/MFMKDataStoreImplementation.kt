package com.foss.core.datastore

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.datastore.preferences.remove
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import javax.inject.Inject

class MFMKDataStoreImplementation @Inject constructor(private val dataStore: DataStore<Preferences>) :
    MFMKDataStore {

    private val TAG = "TAG"

    override suspend fun saveData(key: String, value: Any) {
        try {
            dataStore.edit { preferences ->
                when (value) {
                    is Int -> preferences[preferencesKey<Int>(key)] = value
                    is String -> preferences[preferencesKey<String>(key)] = value
                    is Boolean -> preferences[preferencesKey<Boolean>(key)] = value
                    is Long -> preferences[preferencesKey<Long>(key)] = value
                    is Float -> preferences[preferencesKey<Float>(key)] = value
                    is Double -> preferences[preferencesKey<Double>(key)] = value
                    else -> throw IllegalArgumentException("Unsupported data type")
                }
            }
        } catch (e: Exception) {
            Timber.e("Error saving data: ${e.message}")
        }
    }

    override suspend fun getData(key: String, defaultValue: Any): Any {
        return try {
            val currentPreferences = dataStore.data.first()
            when (defaultValue) {
                is Int -> currentPreferences[preferencesKey<Int>(key)] ?: defaultValue
                is String -> currentPreferences[preferencesKey<String>(key)] ?: defaultValue
                is Boolean -> currentPreferences[preferencesKey<Boolean>(key)] ?: defaultValue
                is Long -> currentPreferences[preferencesKey<Long>(key)] ?: defaultValue
                is Float -> currentPreferences[preferencesKey<Float>(key)] ?: defaultValue
                is Double -> currentPreferences[preferencesKey<Double>(key)] ?: defaultValue
                else -> throw IllegalArgumentException("Unsupported data type")
            }
        } catch (e: Exception) {
            Timber.e("Error getting data: ${e.message}")
            defaultValue
        }
    }

    override fun getDataFlow(key: String, defaultValue: Any): Flow<Any> {
        return dataStore.data.map { currentPreferences ->
            when (defaultValue) {
                is Int -> currentPreferences[preferencesKey<Int>(key)] ?: defaultValue
                is String -> currentPreferences[preferencesKey<String>(key)] ?: defaultValue
                is Boolean -> currentPreferences[preferencesKey<Boolean>(key)] ?: defaultValue
                is Long -> currentPreferences[preferencesKey<Long>(key)] ?: defaultValue
                is Float -> currentPreferences[preferencesKey<Float>(key)] ?: defaultValue
                is Double -> currentPreferences[preferencesKey<Double>(key)] ?: defaultValue
                else -> throw IllegalArgumentException("Unsupported data type")
            }
        }
    }

    override suspend fun clearData(key: String) {
        try {
            dataStore.edit { preferences ->
                preferences.remove(preferencesKey<Preferences>(key))
            }
        } catch (e: Exception) {
            Timber.e("Error clearing data: ${e.message}")
        }
    }

    override suspend fun <R> saveArray(key: String, value: Array<R>) {
        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
            objectOutputStream.writeObject(value)
            val arrayBytes = byteArrayOutputStream.toByteArray()
            dataStore.edit { preferences ->
                preferences[preferencesKey<ByteArray>(key)] = arrayBytes
            }
        } catch (e: Exception) {
            Timber.e("Error saving array: ${e.message}")
        }
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <R> getArray(key: String, defaultValue: Array<R>): Array<R> {
        return try {
            val currentPreferences = dataStore.data.first()
            val arrayBytes =
                currentPreferences[preferencesKey<ByteArray>(key)] ?: return defaultValue
            val byteArrayInputStream = ByteArrayInputStream(arrayBytes)
            val objectInputStream = ObjectInputStream(byteArrayInputStream)
            objectInputStream.readObject() as Array<R>
        } catch (e: Exception) {
            Timber.e("Error getting array: ${e.message}")
            defaultValue
        }
    }

    override suspend fun <R> saveObject(key: String, value: R) {
        try {
            val byteArrayOutputStream = ByteArrayOutputStream()
            val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
            objectOutputStream.writeObject(value)
            val objectBytes = byteArrayOutputStream.toByteArray()
            dataStore.edit { preferences ->
                preferences[preferencesKey<ByteArray>(key)] = objectBytes
            }
        } catch (e: Exception) {
            Timber.e("Error saving object: ${e.message}")
        }
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <R> getObject(key: String, defaultValue: R): R {
        return try {
            val currentPreferences = dataStore.data.first()
            val objectBytes =
                currentPreferences[preferencesKey<ByteArray>(key)] ?: return defaultValue
            val byteArrayInputStream = ByteArrayInputStream(objectBytes)
            val objectInputStream = ObjectInputStream(byteArrayInputStream)
            objectInputStream.readObject() as R
        } catch (e: Exception) {
            Timber.e("Error getting object: ${e.message}")
            defaultValue
        }
    }

    override fun <R> getObjectFlow(key: String, clazz: Class<R>, defaultValue: R): Flow<R> {
        return dataStore.data
            .map { currentPreferences ->
                val objectBytes =
                    currentPreferences[preferencesKey<ByteArray>(key)] ?: return@map defaultValue
                deserializeObject(objectBytes, clazz)
            }
            .flowOn(Dispatchers.IO)
    }

    private suspend fun <R> deserializeObject(data: ByteArray, clazz: Class<R>): R {
        return withContext(Dispatchers.IO) {
            try {
                val byteArrayInputStream = ByteArrayInputStream(data)
                val objectInputStream = ObjectInputStream(byteArrayInputStream)
                val deserializedObject = objectInputStream.readObject()
                if (clazz.isInstance(deserializedObject)) {
                    clazz.cast(deserializedObject)
                } else {
                    throw IllegalArgumentException("Deserialized object is not of expected type")
                }
            } catch (e: Exception) {
                Timber.e("Error deserializing object: ${e.message}")
                throw e
            }
        }
    }

}



