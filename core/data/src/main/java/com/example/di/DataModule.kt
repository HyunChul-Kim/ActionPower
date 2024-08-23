package com.example.di

import com.example.domain.repository.CocktailSearchRepository
import com.example.domain.repository.FavoriteDrinkRepository
import com.example.domain.repository.ScriptRepository
import com.example.repository.CocktailSearchRepositoryImpl
import com.example.repository.FavoriteDrinkRepositoryImpl
import com.example.repository.ScriptRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
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

    @Binds
    abstract fun bindScriptRepository(
        scriptRepository: ScriptRepositoryImpl
    ): ScriptRepository
}