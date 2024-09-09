package com.example.orderapp.data.repositories

import com.example.orderapp.data.local.MealDao
import com.example.orderapp.data.model.Meal
import com.example.orderapp.data.remote.MealService

class MealListRepository(private val mealService: MealService) {

    suspend fun getMealList():List<Meal>{
        return mealService.getMealList()
    }

}