package com.example.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun DetailRoute(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val drinkId by detailViewModel.selectedDrinkId.collectAsStateWithLifecycle()

    DetailScreen(
        modifier = modifier,
        drinkId = drinkId,
        viewModel = detailViewModel
    )
}