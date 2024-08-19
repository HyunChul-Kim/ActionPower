@file:OptIn(ExperimentalStdlibApi::class)

package com.example.domain.usecase

import android.util.Log
import com.example.model.DrinkResource
import com.example.model.SearchResult
import com.example.domain.model.ApiResult
import com.example.domain.repository.CocktailSearchRepository
import com.example.domain.repository.FavoriteDrinkRepository
import com.example.model.UserDrinkResource
import com.example.model.UserSearchResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
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
    ): Flow<ApiResult<UserSearchResult>> = channelFlow {
        var result: List<DrinkResource> = emptyList()
        var favoriteDrinkResources: List<DrinkResource> = emptyList()
        withContext(Dispatchers.IO) {
            if(query.isEmpty()) {
                if(filteredCocktailList.isEmpty()) {
                    cocktailSearchRepository.getCocktailListByFilter(filter.value)
                        .collectLatest { apiResult ->
                            when(apiResult) {
                                is ApiResult.Success ->
                                    filteredCocktailList = apiResult.value.drinkResources
                                is ApiResult.Error ->
                                    send(
                                        ApiResult.Error(
                                            code = apiResult.code,
                                            message = apiResult.message
                                        )
                                    )
                                is ApiResult.Exception ->
                                    send(
                                        ApiResult.Exception(
                                            exception = apiResult.exception
                                        )
                                    )
                            }
                        }
                }
                result = filteredCocktailList
            } else {
                if(query.length == 1) {
                    cocktailSearchRepository.getCocktailListByFirstLetter(query.first().toString()).collectLatest { apiResult ->
                        when(apiResult) {
                            is ApiResult.Success ->
                                result = apiResult.value.drinkResources
                            is ApiResult.Error ->
                                send(
                                    ApiResult.Error(
                                        code = apiResult.code,
                                        message = apiResult.message
                                    )
                                )
                            is ApiResult.Exception ->
                                send(
                                    ApiResult.Exception(
                                        exception = apiResult.exception
                                    )
                                )
                        }
                    }
                } else {
                    cocktailSearchRepository.getCocktailListByName(query).collectLatest { apiResult ->
                        when(apiResult) {
                            is ApiResult.Success ->
                                result = apiResult.value.drinkResources
                            is ApiResult.Error ->
                                send(
                                    ApiResult.Error(
                                        code = apiResult.code,
                                        message = apiResult.message
                                    )
                                )
                            is ApiResult.Exception ->
                                send(
                                    ApiResult.Exception(
                                        exception = apiResult.exception
                                    )
                                )
                        }
                    }
                }
            }
            favoriteDrinkResources = favoriteDrinkRepository.getDrinkResources().first()
        }
        send(
            ApiResult.Success(
                UserSearchResult(
                    drinkResources = result.map { drinkResource ->
                        UserDrinkResource(
                            name = drinkResource.name,
                            thumbnail = drinkResource.thumbnail,
                            id = drinkResource.id,
                            category = drinkResource.category,
                            isFavorite = favoriteDrinkResources
                                .firstOrNull { it.id == drinkResource.id } != null
                        )
                    }
                )
            )
        )
    }
}

enum class CocktailSearchFilter(val value: String) {
    ALCOHOLIC("Alcoholic")
}