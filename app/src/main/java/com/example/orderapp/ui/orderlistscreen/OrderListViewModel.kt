package com.example.orderapp.ui.orderlistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orderapp.data.repositories.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class OrderListViewModel(private val orderRepository: OrderRepository):ViewModel() {
    private val _orderListUIState = MutableStateFlow<OrderListUIState>(OrderListUIState.Loading)
    val orderListUIState = _orderListUIState.asStateFlow()
    init {
        getOrderList()
    }

    fun getOrderList(){
        viewModelScope.launch {
            try {
                orderRepository.getAllOrders().collectLatest {
                    if(it.isEmpty()){
                        _orderListUIState.value = OrderListUIState.EmptyOrderList
                    }else{
                        _orderListUIState.value = OrderListUIState.Success(it)

                    }
                }
            }catch (e:Exception){
                _orderListUIState.value = OrderListUIState.Error
            }
        }
    }
}