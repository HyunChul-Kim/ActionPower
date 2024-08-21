package com.example.search

import com.example.model.DrinkResource
import com.example.model.FavoriteData

sealed interface SearchResultUiState {
    data object Loading: SearchResultUiState
    data class Success(
        val drinks: List<FavoriteData<DrinkResource>>
    ): SearchResultUiState
    data object Error: SearchResultUiState
}