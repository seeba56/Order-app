package com.example.orderapp.di

import com.example.orderapp.data.remote.MealService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModules = module {
    single {
        get<Retrofit>().create(MealService::class.java)
    }
    single {
        Json {
            ignoreUnknownKeys = true
        }
    }
    single {
        Retrofit
            .Builder()
            .baseUrl("https://seeba56.github.io")
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }
}