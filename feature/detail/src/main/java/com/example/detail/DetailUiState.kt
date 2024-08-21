package com.example.detail

import com.example.model.DrinkDetailResource
import com.example.model.FavoriteData

sealed interface DetailUiState {
    data object Loading: DetailUiState
    data class Success(
        val favoriteDrinkDetailData: FavoriteData<DrinkDetailResource>
    ): DetailUiState
    data object Error: DetailUiState
}