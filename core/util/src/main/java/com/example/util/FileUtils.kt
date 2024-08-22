package com.example.util

import android.content.Context
import android.util.Base64
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


fun getFileToStringFromAssets(
    context: Context,
    fileName: String,
    charsetName: String = "utf-8",
    readLineSeparator: String = "\n"
): String {
    getFileFromAssets(context, fileName).use {
        try {
            val bufferedReader = BufferedReader(InputStreamReader(it, charsetName))
            return bufferedReader.readLines().joinToString(readLineSeparator)
        } catch (e: Exception) {
            return ""
        }
    }
}

fun getFileFromAssets(
    context: Context,
    fileName: String
): InputStream? {
    return try {
        context.assets.open(fileName)
    } catch (e: IOException) {
        null
    }
}

fun getBytesFromAssets(
    context: Context,
    fileName: String,
    charsetName: String = "utf-8",
): ByteArray {
    val outputStream = ByteArrayOutputStream()
    getFileFromAssets(context, fileName)
        ?.bufferedReader(Charsets.UTF_8)
        ?.readLines()
        ?.joinToString("")
    return outputStream.toByteArray()
}

fun decodeByBase64FromAssets(
    context: Context,
    fileName: String
): String {
    return decodeByBase64(
        getFileToStringFromAssets(
            context,
            fileName,
            readLineSeparator = ""
            //charsetName = Charsets.UTF_8.name()
        )
    )
}

fun decodeByBase64(bytes: ByteArray): String {
    return try {
        String(Base64.decode(bytes, Base64.DEFAULT))
    } catch (e: UnsupportedOperationException) {
        ""
    } catch (e: IllegalArgumentException) {
        ""
    }
}

fun decodeByBase64(content: String): String {
    return try {
        String(Base64.decode(content.toByteArray(Charsets.UTF_8), Base64.DEFAULT))
    } catch (e: UnsupportedOperationException) {
        ""
    } catch (e: IllegalArgumentException) {
        ""
    }
}