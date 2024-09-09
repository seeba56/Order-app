package com.example.orderapp.ui.orderscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.orderapp.R
import com.example.orderapp.data.model.Meal
import com.example.orderapp.ui.EmptyLisScreen
import com.example.orderapp.ui.ErrorScreen
import com.example.orderapp.ui.destinations.MealDetailScreenDestination
import com.example.orderapp.ui.destinations.OrderScreenDestination
import com.example.orderapp.ui.meallistscreen.MealListScreenContent
import com.example.orderapp.ui.meallistscreen.MealListUIState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun OrderScreen(navigator: DestinationsNavigator) {
    val orderScreenViewModel = getViewModel<OrderScreenViewModel>()
    val uiState by orderScreenViewModel.mealListUIState.collectAsState()
    val isOrderButtonEnabled = orderScreenViewModel.isOrderButtonEnabled.value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Current order") },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { orderScreenViewModel.lockOrder() },
                modifier = Modifier.fillMaxWidth(),
                enabled = isOrderButtonEnabled
            ) {
                Text(text = "Order")
            }
        }
    ) {
        when (uiState) {
            is MealListUIState.Success -> {
                OrderScreenContent(
                    mealList = (uiState as MealListUIState.Success).list,
                    onAddToOrder = { meal ->
                        meal.isInOrder = true

                    },
                    onRemoveFromOrder = { meal ->
                        orderScreenViewModel.removeMealFromOrder(meal)
                        meal.isInOrder = false
                    },
                    onMealItemClick = { meal ->
                        navigator.navigate(MealDetailScreenDestination(meal))
                    },
                    paddingValues = it
                )
            }

            MealListUIState.Loading -> {
                EmptyLisScreen()
            }

            MealListUIState.EmptyMealList -> {
                EmptyLisScreen()

            }

            MealListUIState.Error -> {
                ErrorScreen {
                    orderScreenViewModel.getAllOrderedMeals()
                }

            }
        }
    }
}

@Composable
fun OrderScreenContent(
    mealList: List<Meal>,
    onAddToOrder: (Meal) -> Unit,
    onRemoveFromOrder: (Meal) -> Unit,
    onMealItemClick: (Meal) -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
    ) {
        items(mealList) {
            OrderedMealItem(
                meal = it,
                onAddToOrder = onAddToOrder,
                onRemoveFromOrder = onRemoveFromOrder,
                onMealItemClick = onMealItemClick
            )
        }

    }

}
