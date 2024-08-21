package com.example.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailDetailResponse(
    @SerialName("drinks")
    val drinks: List<CocktailDetailDrink> = emptyList()
)

@Serializable
data class CocktailDetailDrink(
    @SerialName("strDrink")
    val strDrink: String,
    @SerialName("strDrinkThumb")
    val strDrinkThumb: String = "",
    @SerialName("idDrink")
    val idDrink: String,
    @SerialName("strCategory")
    val strCategory: String = "",
    @SerialName("dateModified")
    val dateModified: String = "",
    @SerialName("strIBA")
    val strIBA: String = "",
    @SerialName("strInstructions")
    val strInstructions: String = "",
    @SerialName("strInstructionsES")
    val strInstructionsES: String = "",
    @SerialName("strInstructionsDE")
    val strInstructionsDE: String = "",
    @SerialName("strInstructionsFR")
    val strInstructionsFR: String = "",
    @SerialName("strInstructionsIT")
    val strInstructionsIT: String = "",
    @SerialName("strInstructionsZH-HANS")
    val strInstructionsZhHans: String = "",
    @SerialName("strInstructionsZH-HANT")
    val strInstructionsZhHant: String = "",
    @SerialName("strIngredient1")
    val strIngredient1: String = "",
    @SerialName("strIngredient2")
    val strIngredient2: String = "",
    @SerialName("strIngredient3")
    val strIngredient3: String = "",
    @SerialName("strIngredient4")
    val strIngredient4: String = "",
    @SerialName("strIngredient5")
    val strIngredient5: String = "",
    @SerialName("strIngredient6")
    val strIngredient6: String = "",
    @SerialName("strIngredient7")
    val strIngredient7: String = "",
    @SerialName("strIngredient8")
    val strIngredient8: String = "",
    @SerialName("strIngredient9")
    val strIngredient9: String = "",
    @SerialName("strIngredient10")
    val strIngredient10: String = "",
    @SerialName("strIngredient11")
    val strIngredient11: String = "",
    @SerialName("strIngredient12")
    val strIngredient12: String = "",
    @SerialName("strIngredient13")
    val strIngredient13: String = "",
    @SerialName("strIngredient14")
    val strIngredient14: String = "",
    @SerialName("strIngredient15")
    val strIngredient15: String = "",
)

fun CocktailDetailDrink.toDomain(): DrinkDetailResource {
    val instructions = mapOf(
        Language.Default.value to strInstructions,
        Language.ES.value to strInstructionsES,
        Language.DE.value to strInstructionsDE,
        Language.FR.value to strInstructionsFR,
        Language.IT.value to strInstructionsIT,
        Language.ZhHans.value to strInstructionsZhHans,
        Language.ZhHant.value to strInstructionsZhHant
    ).filter { it.value.isNotEmpty() }
    val ingredients = listOf(
        strIngredient1, strIngredient2,
        strIngredient3, strIngredient4,
        strIngredient5, strIngredient6,
        strIngredient7, strIngredient8,
        strIngredient9, strIngredient10,
        strIngredient11, strIngredient12,
        strIngredient13, strIngredient14,
        strIngredient15,
    ).filter { it.isNotEmpty() }
    return DrinkDetailResource(
        name = strDrink,
        thumbnail = strDrinkThumb,
        id = idDrink,
        category = strCategory,
        dateModified = dateModified,
        iba = strIBA,
        instructions = instructions,
        ingredients = ingredients,
        instructionsToString = instructions.entries.joinToString(
            separator = "\n\n"
        ) { entry ->
            "${entry.key} : ${entry.value}"
        },
        ingredientsToString = ingredients.joinToString()
    )
}