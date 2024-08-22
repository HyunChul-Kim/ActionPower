package com.example.youtubeplayer

import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface

class YoutubePlayerBridge(
    private val youtubePlayerInterface: YoutubePlayerInterface
) {
    private val handler = Handler(Looper.getMainLooper())

    @JavascriptInterface
    fun sendVideoCurrentTime(time: String) {
        handler.post {
            youtubePlayerInterface.sendVideoCurrentTime(time)
        }
    }
}