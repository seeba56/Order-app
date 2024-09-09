package com.example.orderapp.data.remote

import com.example.orderapp.data.model.Meal
import retrofit2.http.GET

interface MealService {
    @GET("/data/data.json")
    suspend fun getMealList():List<Meal>
}