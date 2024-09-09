package com.example.orderapp.ui.meallistscreen

import com.example.orderapp.data.model.Meal

interface MealListUIState{
    object Loading: MealListUIState
    object Error: MealListUIState
    object EmptyMealList: MealListUIState
    data class Success(val list:List<Meal>):MealListUIState
}