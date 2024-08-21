package com.example.model

data class FavoriteData<out T>(
    val isFavorite: Boolean,
    val data: T
)