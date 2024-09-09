package com.example.orderapp.ui.orderscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapp.data.model.Meal
import com.example.orderapp.data.model.Order
import com.example.orderapp.data.repositories.OrderRepository
import com.example.orderapp.ui.meallistscreen.MealListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset

class OrderScreenViewModel(private val orderRepository: OrderRepository) : ViewModel() {
    private val _mealListUIState: MutableStateFlow<MealListUIState> =
        MutableStateFlow(MealListUIState.Loading)
    val mealListUIState = _mealListUIState.asStateFlow()
    private var mealList = listOf<Meal>()
    val isOrderButtonEnabled = mutableStateOf(false)

    init {
        getAllOrderedMeals()
    }

    fun getAllOrderedMeals() {
        viewModelScope.launch {
            try {
                orderRepository.getAllMeals().collect {
                    mealList = it
                    if (it.isEmpty()) {
                        _mealListUIState.value = MealListUIState.EmptyMealList
                        isOrderButtonEnabled.value = false

                    } else {
                        _mealListUIState.value = MealListUIState.Success(it)
                        isOrderButtonEnabled.value = true
                    }
                }
            } catch (e: Exception) {
                _mealListUIState.value = MealListUIState.Error
                isOrderButtonEnabled.value = false

            }

        }
    }

    fun removeMealFromOrder(meal: Meal) {
        viewModelScope.launch {
            orderRepository.deleteMeal(meal)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun lockOrder() {
        viewModelScope.launch {
            orderRepository.insertOrder(
                Order(
                    mealList = mealList, time = LocalDateTime.now().toEpochSecond(
                        ZoneOffset.UTC
                    )
                )
            )
            orderRepository.deleteAllMeals()
        }
    }
}