package com.example.api

import com.example.model.CocktailSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailSearchService {
    @GET("v1/1/filter.php")
    suspend fun getFilteredSearchResponse(
        @Query("a") filter: String,
    ): CocktailSearchResponse
}