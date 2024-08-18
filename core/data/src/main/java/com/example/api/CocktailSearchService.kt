package com.example.api

import com.example.model.CocktailSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailSearchService {
    @GET("v1/1/filter.php")
    suspend fun getSearchByFilterResponse(
        @Query("a") filter: String,
    ): CocktailSearchResponse

    @GET("v1/1/search.php")
    suspend fun getSearchByFirstLetterResponse(
        @Query("f") letter: String
    ): CocktailSearchResponse

    @GET("v1/1/search.php")
    suspend fun getSearchByNameResponse(
        @Query("s") name: String
    ): CocktailSearchResponse
}