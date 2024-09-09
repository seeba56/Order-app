package com.example.orderapp.di

import com.example.orderapp.ui.mealdetailscreen.MealDetailsViewModel
import com.example.orderapp.ui.meallistscreen.MealListViewModel
import com.example.orderapp.ui.orderlistscreen.OrderListViewModel
import com.example.orderapp.ui.orderscreen.OrderScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MealListViewModel(get(),get()) }
    viewModel { MealDetailsViewModel(get()) }
    viewModel {OrderScreenViewModel(get())}
    viewModel { OrderListViewModel(get()) }
}

