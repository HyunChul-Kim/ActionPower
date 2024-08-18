package com.example.di

import com.example.domain.repository.CocktailSearchRepository
import com.example.repository.CocktailSearchRepositoryImpl
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
    abstract fun bindsCocktailSearchRepository(
        cocktailSearchRepository: CocktailSearchRepositoryImpl
    ): CocktailSearchRepository
}