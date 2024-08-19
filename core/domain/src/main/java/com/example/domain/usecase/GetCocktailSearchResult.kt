package com.example.domain.usecase

import com.example.model.DrinkResource
import com.example.model.SearchResult
import com.example.domain.model.ApiResult
import com.example.domain.repository.CocktailSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCocktailSearchResult @Inject constructor(
    private val cocktailSearchRepository: CocktailSearchRepository
) {
    private var filter = CocktailSearchFilter.ALCOHOLIC
    private var currentQuery = ""

    private var filteredCocktailList: List<DrinkResource> = emptyList()

    operator fun invoke(
        query: String = currentQuery,
    ): Flow<ApiResult<SearchResult>> = channelFlow {
        var result: List<DrinkResource> = emptyList()
        if(query.isEmpty()) {
            if(filteredCocktailList.isEmpty()) {
                withContext(Dispatchers.IO) {
                    cocktailSearchRepository.getCocktailListByFilter(filter.value)
                        .collectLatest { apiResult ->
                            if (apiResult is ApiResult.Success) {
                                filteredCocktailList = apiResult.value.drinkResources
                            } else {
                                send(apiResult)
                            }
                        }
                }
            }
            result = filteredCocktailList
        } else {
            withContext(Dispatchers.IO) {
                if(query.length == 1) {
                    cocktailSearchRepository.getCocktailListByFirstLetter(query.first().toString()).collectLatest { apiResult ->
                        if(apiResult is ApiResult.Success) {
                            result = apiResult.value.drinkResources
                        } else {
                            send(apiResult)
                        }
                    }
                } else {
                    cocktailSearchRepository.getCocktailListByName(query).collectLatest { apiResult ->
                        if(apiResult is ApiResult.Success) {
                            result = apiResult.value.drinkResources
                        } else {
                            send(apiResult)
                        }
                    }
                }
            }
        }
        send(
            ApiResult.Success(
                SearchResult(
                    drinkResources = result
                )
            )
        )
    }
}

enum class CocktailSearchFilter(val value: String) {
    ALCOHOLIC("Alcoholic")
}