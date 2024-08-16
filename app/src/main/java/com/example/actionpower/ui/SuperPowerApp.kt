package com.example.actionpower.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.actionpower.navigation.SuperPowerNavHost

@Composable
fun SuperPowerApp() {
    val navHostController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)) {
            SuperPowerNavHost(
                modifier = Modifier
                    .fillMaxSize(),
                navController = navHostController,
            )
        }
    }
}