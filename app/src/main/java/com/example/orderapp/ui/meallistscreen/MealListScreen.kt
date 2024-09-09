package com.example.orderapp.ui.meallistscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.orderapp.R
import com.example.orderapp.data.model.Meal
import com.example.orderapp.ui.EmptyLisScreen
import com.example.orderapp.ui.ErrorScreen
import com.example.orderapp.ui.destinations.MealDetailScreenDestination
import com.example.orderapp.ui.destinations.OrderListScreenDestination
import com.example.orderapp.ui.destinations.OrderScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Composable
fun MealListScreen(navigator: DestinationsNavigator) {
    val mealListViewModel = getViewModel<MealListViewModel>()
    val selectedOption = rememberSaveable {
        mutableStateOf("")
    }
    mealListViewModel.getMealList(selectedOption.value)
    val uiState = mealListViewModel.mealListUIState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Menu") },
                actions = {
                    IconButton(onClick = {navigator.navigate(OrderListScreenDestination)}) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_history_24),
                            contentDescription = null
                        )

                    }
                    IconButton(onClick = {navigator.navigate(OrderScreenDestination)}) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_shopping_bag_24),
                            contentDescription = null
                        )

                    }
                })
        }
    ) {
        when (uiState.value) {
            is MealListUIState.Success -> {
                MealListScreenContent(
                    mealList = (uiState.value as MealListUIState.Success).list,
                    onMealItemClick = {meal ->
                        navigator.navigate(MealDetailScreenDestination(meal))},
                    selectedOptionValue = selectedOption.value,
                    onIconRadioButtonClick = { selectedOption.value = it },
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
                    mealListViewModel.getMealList(selectedOption.value)
                }

            }
        }
    }





}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealListScreenContent(
    mealList: List<Meal>,
    onMealItemClick: (Meal) -> Unit,
    selectedOptionValue: String,
    onIconRadioButtonClick: (String) -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, top = paddingValues.calculateTopPadding())
    ) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconRadioButton(
                    isSelected = selectedOptionValue == "",
                    onClick = onIconRadioButtonClick,
                    value = "",
                    resDrawable = R.drawable.outline_dangerous_24
                )
                IconRadioButton(
                    isSelected = selectedOptionValue == "Pizza",
                    onClick = onIconRadioButtonClick,
                    value = "Pizza",
                    resDrawable = R.drawable.outline_local_pizza_24
                )
                IconRadioButton(
                    isSelected = selectedOptionValue == "Burger",
                    onClick = onIconRadioButtonClick,
                    value = "Burger",
                    resDrawable = R.drawable.fast_food_burger
                )
                IconRadioButton(
                    isSelected = selectedOptionValue == "Grill",
                    onClick = onIconRadioButtonClick,
                    value = "Grill",
                    resDrawable = R.drawable.meat_on_the_bone
                )
                IconRadioButton(
                    isSelected = selectedOptionValue == "Side dish",
                    onClick = onIconRadioButtonClick,
                    value = "Side dish",
                    resDrawable = R.drawable.french_fries
                )
                IconRadioButton(
                    isSelected = selectedOptionValue == "Dessert",
                    onClick = onIconRadioButtonClick,
                    value = "Dessert",
                    resDrawable = R.drawable.cupcake
                )
            }
        }
        items(mealList) {
            MealListItem(
                meal = it,
                onMealItemClick = onMealItemClick
            )
        }
    }
}