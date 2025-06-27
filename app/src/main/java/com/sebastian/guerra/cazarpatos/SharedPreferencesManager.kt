package com.sebastian.guerra.cazarpatos

import android.app.Activity
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SharedPreferencesManager(val actividad: Activity) : FileHandler {

    private val sharedPref: SharedPreferences by lazy {
        val masterKey = MasterKey.Builder(actividad)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        EncryptedSharedPreferences.create(
            actividad,
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        val editor = sharedPref.edit()
        editor.putString(LOGIN_KEY, datosAGrabar.first)
        editor.putString(PASSWORD_KEY, datosAGrabar.second)
        editor.apply()
    }

    override fun ReadInformation(): Pair<String, String> {
        val email = sharedPref.getString(LOGIN_KEY, "") ?: ""
        val clave = sharedPref.getString(PASSWORD_KEY, "") ?: ""
        return (email to clave)
    }
}