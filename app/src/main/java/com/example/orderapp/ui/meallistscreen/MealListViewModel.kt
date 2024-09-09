package com.example.orderapp.ui.meallistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapp.data.model.Meal
import com.example.orderapp.data.repositories.MealListRepository
import com.example.orderapp.data.repositories.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MealListViewModel(
    private val mealListRepository: MealListRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _mealListUIState: MutableStateFlow<MealListUIState> =
        MutableStateFlow(MealListUIState.Loading)
    val mealListUIState = _mealListUIState.asStateFlow()


    fun getMealList(filter:String) {
        viewModelScope.launch {
            try {
                val data = mealListRepository.getMealList()
                if (data.isEmpty()) {
                    _mealListUIState.value = MealListUIState.EmptyMealList
                } else {
                    _mealListUIState.value = MealListUIState.Success(data.filter {
                        it.category.contains(filter)
                    })
                }
            } catch (e: Exception) {
                println(e.message)
                _mealListUIState.value = MealListUIState.Error
            }
        }
    }
}