package com.example.orderapp.ui.orderlistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.orderapp.data.model.Order
import com.example.orderapp.ui.EmptyLisScreen
import com.example.orderapp.ui.ErrorScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun OrderListScreen(navigator: DestinationsNavigator) {
    val orderListViewModel = koinViewModel<OrderListViewModel>()
    val orderListUIState by orderListViewModel.orderListUIState.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(text = "Previous orders") }, navigationIcon = {
            IconButton(onClick = { navigator.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        })
    }) {
        when(orderListUIState){
            OrderListUIState.EmptyOrderList -> {
                EmptyLisScreen()
            }

            OrderListUIState.Error -> {
                ErrorScreen {
                    orderListViewModel.getOrderList()
                }

            }
            OrderListUIState.Loading -> {
                EmptyLisScreen()

            }
            is OrderListUIState.Success -> {
                OrderListScreenContent(orderList = (orderListUIState as OrderListUIState.Success).list, paddingValues = it)
            }
        }
    }

}

@Composable
fun OrderListScreenContent(orderList: List<Order>, paddingValues: PaddingValues) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(top = paddingValues.calculateTopPadding())){
        items(orderList){
            OrderListItem(order = it)

        }
    }

}