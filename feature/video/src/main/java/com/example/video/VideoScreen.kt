package com.example.video

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.model.SectionScript
import com.example.script.ScriptView
import com.example.youtubeplayer.YoutubePlayer

@Composable
fun VideoScreen(
    modifier: Modifier = Modifier,
    updateCurrentTime: (String) -> Unit,
    sectionScripts: () -> List<SectionScript>,
    interval: Int = 1000
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        YoutubePlayer(
            updateCurrentTime = updateCurrentTime,
            interval = interval
        )
        ScriptView(
            modifier = Modifier.fillMaxSize(),
            sectionScripts = sectionScripts
        )
    }
}