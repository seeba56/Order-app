package com.example.orderapp.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.orderapp.data.local.ListConverter
import com.example.orderapp.data.local.MealDao
import com.example.orderapp.data.local.OrderDao

@TypeConverters(ListConverter::class)
@Database(entities = [Meal::class, Order::class], version = 5)
abstract class MealDatabase:RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun orderDao(): OrderDao
}