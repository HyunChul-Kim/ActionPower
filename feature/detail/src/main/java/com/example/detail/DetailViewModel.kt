package com.example.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiResult
import com.example.domain.repository.CocktailSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    cocktailSearchRepository: CocktailSearchRepository
): ViewModel() {

    private val selectedDrinkId = savedStateHandle.get<String>(DRINK_ID_ARG) ?: ""

    val detailUiState: StateFlow<DetailUiState> = cocktailSearchRepository
        .getCocktailDetailById(selectedDrinkId)
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
}