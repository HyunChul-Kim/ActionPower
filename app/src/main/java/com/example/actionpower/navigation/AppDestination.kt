package com.example.actionpower.navigation

import com.example.search.SEARCH_ROUTE
import com.example.video.VIDEO_ROUTE

enum class AppDestination(
    val route: String,
) {
    SEARCH(SEARCH_ROUTE),
    VIDEO(VIDEO_ROUTE)
}