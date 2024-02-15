package com.foss.shared.common

import androidx.datastore.preferences.preferencesKey


object PreferenceKeys {

    object Keys {
        val email = preferencesKey<String>("email")
        val password = preferencesKey<String>("password")
        val phoneNo = preferencesKey<String>("phone_no")
        val fullName = preferencesKey<String>("full_name")
        val token = preferencesKey<String>("token")
        const val preferenceName = ("shared_preference")

    }
}