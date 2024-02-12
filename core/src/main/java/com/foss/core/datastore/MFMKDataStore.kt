package com.foss.core.datastore

import kotlinx.coroutines.flow.Flow

interface MFMKDataStore {

    suspend fun saveData(key: String, value: Any)

    suspend fun getData(key: String, defaultValue: Any): Any

    fun getDataFlow(key: String, defaultValue: Any): Flow<Any>

    suspend fun clearData(key: String)

    suspend fun <R> saveArray(key: String, value: Array<R>)

    suspend fun <R> getArray(key: String, defaultValue: Array<R>): Array<R>

    suspend fun <R> saveObject(key: String, value: R)

    suspend fun <R> getObject(key: String, defaultValue: R): R

    fun <R> getObjectFlow(key: String, clazz: Class<R>, defaultValue: R): Flow<R>
}



