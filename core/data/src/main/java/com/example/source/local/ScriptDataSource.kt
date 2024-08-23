package com.example.source.local

import android.content.Context
import android.util.Log
import com.example.model.LocalScriptData
import com.example.util.getFileToStringFromAssets
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.jvm.Throws

class ScriptDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    @Throws(
        SerializationException::class,
        IllegalArgumentException::class
    )
    suspend fun getScriptData(fileName: String) = withContext(Dispatchers.IO) {
        val fileToString = getFileToStringFromAssets(
            context = context,
            fileName = fileName
        )
        Log.d("#chul", "[ScriptDataSource] file name is $fileName")
        Log.d("#chul", "[ScriptDataSource] $fileToString")
        Json.decodeFromString<LocalScriptData>(fileToString)
    }
}