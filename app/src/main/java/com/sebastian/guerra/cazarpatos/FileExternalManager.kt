// File: app/src/main/java/com/sebastian/guerra/cazarpatos/FileExternalManager.kt
package com.sebastian.guerra.cazarpatos

import android.app.Activity
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

const val SHAREDINFO_FILENAME = "mySharedInformation.dat"

class FileExternalManager(val actividad: Activity) : FileHandler {
    private fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        if (isExternalStorageWritable()) {
            FileOutputStream(
                File(actividad.getExternalFilesDir(null), SHAREDINFO_FILENAME)
            ).bufferedWriter().use { outputStream ->
                outputStream.write(datosAGrabar.first)
                outputStream.write(System.lineSeparator())
                outputStream.write(datosAGrabar.second)
            }
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        try {
            if (isExternalStorageReadable()) {
                FileInputStream(
                    File(actividad.getExternalFilesDir(null), SHAREDINFO_FILENAME)
                ).bufferedReader().use {
                    val datoLeido = it.readText()
                    val textArray = datoLeido.split(System.lineSeparator())
                    val email = textArray.getOrNull(0) ?: ""
                    val clave = textArray.getOrNull(1) ?: ""
                    return (email to clave)
                }
            }
        } catch (e: Exception) {
        }
        return ("" to "")
    }
}