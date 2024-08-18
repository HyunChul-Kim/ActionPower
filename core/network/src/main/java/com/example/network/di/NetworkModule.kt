package com.example.network.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .client(
                OkHttpClient.Builder().run {
                    addInterceptor(
                        HttpLoggingInterceptor { log ->
                            Log.d("OkHttp", log)
                        }.apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    build()
                }
            )
            .build()

    private val networkJson: Json = Json {
        ignoreUnknownKeys = true
    }
}