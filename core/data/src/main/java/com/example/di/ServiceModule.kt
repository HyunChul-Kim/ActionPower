package com.example.di

import com.example.api.CocktailSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {
    @Provides
    @Singleton
    fun provideCocktailSearchService(
        retrofit: Retrofit
    ) = retrofit.create<CocktailSearchService>()
}