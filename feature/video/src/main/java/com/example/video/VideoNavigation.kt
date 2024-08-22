package com.example.video

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val VIDEO_ROUTE = "video_route"

fun NavController.navigateToVideo(navOptions: NavOptions) = navigate(VIDEO_ROUTE, navOptions)

fun NavGraphBuilder.videoScreen() {
    composable(route = VIDEO_ROUTE) {
        VideoRoute()
    }
}