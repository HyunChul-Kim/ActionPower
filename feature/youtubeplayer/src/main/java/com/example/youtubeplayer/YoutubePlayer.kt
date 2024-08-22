package com.example.youtubeplayer

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.children
import com.example.util.getFileToStringFromAssets

@Composable
fun YoutubePlayer(
    updateCurrentTime: (String) -> Unit,
    interval: Int = 1000
) {
    val videoId = "Dd1N9NrPt3A"
    val options = YoutubePlayerOptions(
        autoPlay = true,
        start = 0
    ).build()
    var webView: WebView? = WebView(LocalContext.current).apply {
        this.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        webViewClient = WebViewClient()
        webChromeClient = WebChromeClient()
        settings.javaScriptEnabled = true
        addJavascriptInterface(
            YoutubePlayerBridge(
                object : YoutubePlayerInterface {
                    override fun sendVideoCurrentTime(time: String) {
                        updateCurrentTime(time)
                    }
                }
            ),
            "YoutubePlayerAndroid"
        )
    }
    Column {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.78f),
            factory = {
                FrameLayout(it).apply {
                    addView(webView)
                }
            },
            update = {
                val htmlToString = getFileToStringFromAssets(
                    context = it.context,
                    fileName = "iframe_youtube.html"
                ).replace("<<arg_interval>>", "$interval")
                    .replace("<<arg_video_id>>", videoId)
                    .replace("<<arg_player_vars>>", options)
                webView?.loadDataWithBaseURL("https://www.youtube.com", htmlToString, "text/html", "utf-8", null)
            },
            onRelease = { parentFrame ->
                Log.d("#chul", "release")
                (parentFrame.children.first() as? WebView)?.let {
                    parentFrame.removeView(it)
                    webView?.destroy()
                    webView = null
                }
            }
        )
    }
    
}