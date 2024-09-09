package com.example.orderapp.di

import androidx.room.Room
import com.example.orderapp.data.model.MealDatabase
import org.koin.dsl.module

val dbModules = module {
    single {
        get<MealDatabase>().mealDao()
    }
    single {
        get<MealDatabase>().orderDao()
    }
    single {
        Room.databaseBuilder(
            get(),
            MealDatabase::class.java,
            "meal_database"
        ).fallbackToDestructiveMigration()
            .build()
    }
}