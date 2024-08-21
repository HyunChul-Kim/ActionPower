package com.example.domain.usecase

import com.example.domain.model.ApiResult
import com.example.domain.repository.CocktailSearchRepository
import com.example.domain.repository.FavoriteDrinkRepository
import com.example.model.DrinkDetailResource
import com.example.model.FavoriteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetCocktailDetailResult @Inject constructor(
    private val cocktailSearchRepository: CocktailSearchRepository,
    private val favoriteDrinkRepository: FavoriteDrinkRepository
)  {
    operator fun invoke(
        id: String
    ): Flow<ApiResult<FavoriteData<DrinkDetailResource>>> =
        cocktailSearchRepository.getCocktailDetailById(id).combine(
            favoriteDrinkRepository.getDrinkResources()
        ) { apiResult, favoriteDrinkResources ->
            when(apiResult) {
                is ApiResult.Success -> {
                    ApiResult.Success(
                        FavoriteData(
                            data = apiResult.value,
                            isFavorite = favoriteDrinkResources
                                .firstOrNull { it.id == apiResult.value.id } != null
                        )
                    )
                }
                is ApiResult.Error ->
                    ApiResult.Error(
                        code = apiResult.code,
                        message = apiResult.message
                    )
                is ApiResult.Exception ->
                    ApiResult.Exception(
                        exception = apiResult.exception
                    )
            }
        }
}