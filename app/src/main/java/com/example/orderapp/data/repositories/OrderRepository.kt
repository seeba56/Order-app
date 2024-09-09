package com.example.orderapp.data.repositories

import com.example.orderapp.data.model.Meal
import com.example.orderapp.data.local.MealDao
import com.example.orderapp.data.local.OrderDao
import com.example.orderapp.data.model.Order
import kotlinx.coroutines.flow.Flow

class OrderRepository(private val mealDao: MealDao, private val orderDao: OrderDao) {

    fun getAllMeals():Flow<List<Meal>>{
        return mealDao.getAllMeals()
    }

    suspend fun deleteMeal(meal: Meal){
        mealDao.deleteMeal(meal)
    }

    suspend fun insertMeal(meal: Meal){
        mealDao.insertMeal(meal)
    }

    suspend fun insertOrder(order: Order){
        orderDao.insertOrder(order)
    }

    fun getAllOrders():Flow<List<Order>>{
        return orderDao.getAllOrders()
    }

    suspend fun deleteAllMeals(){
        mealDao.deleteAllMeals()
    }
}