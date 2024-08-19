package com.example.domain.usecase

import com.example.model.SearchResult
import com.example.domain.model.ApiResult
import com.example.domain.repository.CocktailSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilteredCocktailList @Inject constructor(
    private val cocktailSearchRepository: CocktailSearchRepository
) {
    operator fun invoke(filter: CocktailSearchFilter): Flow<ApiResult<com.example.model.SearchResult>> =
        cocktailSearchRepository.getCocktailListByFilter(
            filter = filter.value
        )
}