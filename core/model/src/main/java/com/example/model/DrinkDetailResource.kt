package com.example.model

data class DrinkDetailResource(
    val name: String,
    val thumbnail: String,
    val id: String,
    val category: String,
    val dateModified: String = "",
    val iba: String = "",
    val instructions: Map<String, String>,
    val ingredients: List<String>,
    val instructionsToString: String,
    val ingredientsToString: String
)

enum class Language(
    val value: String
) {
    Default("EN"),
    ES("ES"),
    DE("DE"),
    FR("FR"),
    IT("IT"),
    ZhHans("ZH-HANS"),
    ZhHant("ZH-HANT")
}