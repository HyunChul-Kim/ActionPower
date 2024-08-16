package com.example.actionpower.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.search.searchScreen

@Composable
internal fun SuperPowerNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestination.SEARCH.route) {
        searchScreen()
    }
}