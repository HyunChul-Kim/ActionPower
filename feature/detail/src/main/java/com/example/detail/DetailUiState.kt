package com.example.detail

import com.example.model.DrinkDetailResource

sealed interface DetailUiState {
    data object Loading: DetailUiState
    data class Success(
        val drinkDetailResource: DrinkDetailResource
    ): DetailUiState
    data object Error: DetailUiState
}