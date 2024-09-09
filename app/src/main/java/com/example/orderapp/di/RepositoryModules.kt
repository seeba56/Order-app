package com.example.orderapp.di

import com.example.orderapp.data.repositories.MealListRepository
import com.example.orderapp.data.repositories.OrderRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        MealListRepository(get())
    }
    single {
        OrderRepository(get(),get())
    }
}