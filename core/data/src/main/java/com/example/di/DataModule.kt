package com.example.di

import com.example.domain.repository.CocktailSearchRepository
import com.example.domain.repository.FavoriteDrinkRepository
import com.example.repository.CocktailSearchRepositoryImpl
import com.example.repository.FavoriteDrinkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindCocktailSearchRepository(
        cocktailSearchRepository: CocktailSearchRepositoryImpl
    ): CocktailSearchRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteDrinkRepository(
        favoriteDrinkRepository: FavoriteDrinkRepositoryImpl
    ): FavoriteDrinkRepository
}