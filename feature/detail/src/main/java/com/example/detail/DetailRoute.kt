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
    val detailUiState by detailViewModel.detailUiState.collectAsStateWithLifecycle()

    DetailScreen(
        modifier = modifier,
        detailUiState = detailUiState,
        onClickFavorite = detailViewModel::onToggleFavorite
    )
}