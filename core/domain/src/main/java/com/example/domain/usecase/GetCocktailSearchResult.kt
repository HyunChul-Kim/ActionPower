package com.example.domain.usecase

import com.example.app.core.model.SearchResult
import com.example.domain.model.ApiResult
import com.example.domain.repository.CocktailSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCocktailSearchResult @Inject constructor(
    private val cocktailSearchRepository: CocktailSearchRepository
) {
    private var filter = CocktailSearchFilter.ALCOHOLIC
    private var currentQuery = ""

    operator fun invoke(
        query: String = currentQuery,
    ): Flow<ApiResult<SearchResult>> {
        return cocktailSearchRepository.getFilteredCocktailList(filter.value)
    }
}

enum class CocktailSearchFilter(val value: String) {
    ALCOHOLIC("Alcoholic")
}