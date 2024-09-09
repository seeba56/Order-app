package com.example.orderapp.ui.mealdetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapp.data.model.Meal
import com.example.orderapp.data.repositories.OrderRepository
import kotlinx.coroutines.launch

class MealDetailsViewModel(private val orderRepository: OrderRepository):ViewModel() {
    fun insertMeal(meal: Meal){
        viewModelScope.launch {
            orderRepository.insertMeal(meal)
        }
    }

    fun deleteMeal(meal: Meal){
        viewModelScope.launch {
            orderRepository.deleteMeal(meal)
        }
    }
}