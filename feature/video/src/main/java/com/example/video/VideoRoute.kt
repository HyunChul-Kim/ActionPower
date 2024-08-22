package com.example.video

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun VideoRoute(
    modifier: Modifier = Modifier,
    videoViewModel: VideoViewModel = hiltViewModel()
) {

    VideoScreen(
        modifier = modifier,
        updateCurrentTime = videoViewModel::updateCurrentTime,
    )
}