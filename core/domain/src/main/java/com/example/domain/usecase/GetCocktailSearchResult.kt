package com.example.domain.usecase

import com.example.model.DrinkResource
import com.example.model.SearchResult
import com.example.domain.model.ApiResult
import com.example.domain.repository.CocktailSearchRepository
import com.example.domain.repository.FavoriteDrinkRepository
import com.example.model.UserDrinkResource
import com.example.model.UserSearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetCocktailSearchResult @Inject constructor(
    private val cocktailSearchRepository: CocktailSearchRepository,
    private val favoriteDrinkRepository: FavoriteDrinkRepository
) {
    private var filter = CocktailSearchFilter.ALCOHOLIC
    private var currentQuery = ""

    private var filteredCocktailList: List<DrinkResource> = emptyList()

    operator fun invoke(
        query: String = currentQuery,
    ): Flow<ApiResult<UserSearchResult>> {
        val apiFlow = if(query.isEmpty()) {
            if(filteredCocktailList.isEmpty()) {
                cocktailSearchRepository.getCocktailListByFilter(filter.value)
                    .onEach { apiResult ->
                        if(apiResult is ApiResult.Success) {
                            filteredCocktailList = apiResult.value.drinkResources
                        }
                    }
            } else {
                flow {
                    emit(
                        ApiResult.Success(
                            SearchResult(
                                drinkResources = filteredCocktailList
                            )
                        )
                    )
                }
            }
        } else {
            if(query.length == 1) {
                cocktailSearchRepository.getCocktailListByFirstLetter(query.first().toString())
            } else {
                cocktailSearchRepository.getCocktailListByName(query)
            }
        }
        return apiFlow.combine(favoriteDrinkRepository.getDrinkResources()) { apiResult, favoriteDrinkResources ->
            when(apiResult) {
                is ApiResult.Success -> {
                    ApiResult.Success(
                        UserSearchResult(
                            drinkResources = apiResult.value.drinkResources.map { drinkResource ->
                                UserDrinkResource(
                                    isFavorite = favoriteDrinkResources
                                        .firstOrNull { it.id == drinkResource.id } != null,
                                    drinkResource = drinkResource
                                )
                            }
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
}

enum class CocktailSearchFilter(val value: String) {
    ALCOHOLIC("Alcoholic")
}