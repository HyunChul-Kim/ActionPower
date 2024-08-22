package com.example.actionpower.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.detail.detailScreen
import com.example.detail.navigateToDetail
import com.example.search.searchScreen
import com.example.video.videoScreen

@Composable
internal fun SuperPowerNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestination.SEARCH.route) {
        searchScreen(
            onDrinkItemClick = navController::navigateToDetail
        )
        detailScreen()
        videoScreen()
    }
}