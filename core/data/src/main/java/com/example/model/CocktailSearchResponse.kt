package com.example.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailSearchResponse(
    @SerialName("drinks")
    val drinks: List<CocktailSearchDrink> = emptyList(),
)

@Serializable
data class CocktailSearchDrink(
    @SerialName("strDrink")
    val strDrink: String,
    @SerialName("strDrinkThumb")
    val strDrinkThumb: String = "",
    @SerialName("idDrink")
    val idDrink: String,
    @SerialName("strCategory")
    val strCategory: String = "",
)

fun CocktailSearchResponse.toDomain() = SearchResult(
    drinkResources = drinks.map { item ->
        DrinkResource(
            name = item.strDrink,
            thumbnail = item.strDrinkThumb,
            id = item.idDrink,
            category = item.strCategory,
        )
    }
)