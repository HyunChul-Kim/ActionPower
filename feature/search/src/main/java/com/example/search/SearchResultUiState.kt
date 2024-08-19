package com.example.search

import com.example.model.UserDrinkResource

sealed interface SearchResultUiState {
    data object Loading: SearchResultUiState
    data class Success(
        val drinks: List<UserDrinkResource>
    ): SearchResultUiState
    data object Error: SearchResultUiState
}