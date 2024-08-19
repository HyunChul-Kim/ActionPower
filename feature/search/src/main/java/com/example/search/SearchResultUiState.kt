package com.example.search

import com.example.model.DrinkResource

sealed interface SearchResultUiState {
    data object Loading: SearchResultUiState
    data class Success(
        val drinks: List<com.example.model.DrinkResource>
    ): SearchResultUiState
    data object Error: SearchResultUiState
}