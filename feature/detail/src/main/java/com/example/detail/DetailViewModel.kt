package com.example.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiResult
import com.example.domain.repository.CocktailSearchRepository
import com.example.domain.repository.FavoriteDrinkRepository
import com.example.domain.usecase.GetCocktailDetailResult
import com.example.model.DrinkDetailResource
import com.example.model.DrinkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCocktailDetailResult: GetCocktailDetailResult,
    private val favoriteDrinkRepository: FavoriteDrinkRepository
): ViewModel() {

    private val selectedDrinkId = savedStateHandle.get<String>(DRINK_ID_ARG) ?: ""

    val detailUiState: StateFlow<DetailUiState> = getCocktailDetailResult(selectedDrinkId)
        .map { apiResult ->
            when(apiResult) {
                is ApiResult.Success -> {
                    DetailUiState.Success(
                        apiResult.value
                    )
                }
                else -> {
                    DetailUiState.Error
                }
            }
        }.onStart {
            DetailUiState.Loading
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailUiState.Loading
        )

    fun onToggleFavorite(drinkDetailResource: DrinkDetailResource, isFavorite: Boolean) {
        viewModelScope.launch {
            if(isFavorite) {
                favoriteDrinkRepository.insertDrinkResource(drinkDetailResource)
            } else {
                favoriteDrinkRepository.deleteDrinkResource(
                    drinkDetailResource.id
                )
            }
        }
    }
}