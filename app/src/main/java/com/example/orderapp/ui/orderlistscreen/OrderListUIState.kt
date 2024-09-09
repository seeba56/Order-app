package com.example.orderapp.ui.orderlistscreen

import com.example.orderapp.data.model.Order

sealed interface OrderListUIState{
    object Loading: OrderListUIState
    object Error: OrderListUIState
    object EmptyOrderList: OrderListUIState
    data class Success(val list:List<Order>): OrderListUIState
}