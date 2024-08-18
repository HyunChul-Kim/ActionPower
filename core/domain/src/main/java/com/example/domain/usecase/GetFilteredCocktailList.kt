package com.example.domain.usecase

import com.example.app.core.model.SearchResult
import com.example.domain.model.ApiResult
import com.example.domain.repository.CocktailSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilteredCocktailList @Inject constructor(
    private val cocktailSearchRepository: CocktailSearchRepository
) {
    operator fun invoke(filter: CocktailSearchFilter): Flow<ApiResult<SearchResult>> =
        cocktailSearchRepository.getCocktailListByFilter(
            filter = filter.value
        )
}