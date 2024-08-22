package com.example.youtubeplayer

import org.json.JSONException
import org.json.JSONObject

data class YoutubePlayerOptions(
    val autoPlay: Boolean = false,
    val mute: Boolean = true,
    val start: Int = 0
) {
    fun build(): String {
        return JSONObject().apply {
            safePut(AUTO_PLAY, autoPlay.toInt())
            safePut(MUTE, mute.toInt())
            safePut(START, start)
        }.toString()
    }

    companion object {
        private const val AUTO_PLAY = "autoplay"
        private const val MUTE = "mute"
        private const val START = "start"
    }
}

fun Boolean.toInt(): Int =
    if(this) {
        1
    } else {
        0
    }

fun JSONObject.safePut(key: String, value: Int) {
    try {
        put(key, value)
    } catch (e: JSONException) {
        throw RuntimeException("{$key : $value} is invalid")
    }
}

fun JSONObject.safePut(key: String, value: Any) {
    try {
        put(key, value)
    } catch (e: JSONException) {
        throw RuntimeException("{$key : $value} is invalid")
    }
}