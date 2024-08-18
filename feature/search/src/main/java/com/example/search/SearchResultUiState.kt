package com.example.search

import com.example.app.core.model.DrinkResource

sealed interface SearchResultUiState {
    data object Loading: SearchResultUiState
    data class Success(
        val drinks: List<DrinkResource>
    ): SearchResultUiState
    data object Error: SearchResultUiState
}