package com.example.orderapp.ui.mealdetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.orderapp.R
import com.example.orderapp.data.model.Meal
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun MealDetailScreen(meal: Meal, navigator: DestinationsNavigator) {
    val mealDetailsViewModel = getViewModel<MealDetailsViewModel>()
    val isFavourite = rememberSaveable() {
        mutableStateOf(meal.isInOrder)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "${meal.name}, ${meal.price}") }, actions = {
                IconButton(
                    onClick = {
                        if (isFavourite.value) {
                            meal.isInOrder = false
                            isFavourite.value = false
                            mealDetailsViewModel.deleteMeal(meal)

                        } else {
                            meal.isInOrder = true
                            isFavourite.value = true
                            mealDetailsViewModel.insertMeal(meal)
                        }
                    }) {
                    Icon(
                        painter = painterResource(
                            id = if (isFavourite.value) {
                                R.drawable.baseline_shopping_cart_24
                            } else {
                                R.drawable.outline_shopping_cart_24
                            }
                        ),
                        contentDescription = null
                    )
                }
            },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                })
        }
    ) {
        MealDetailScreenContent(meal = meal, paddingValues = it)

    }


}

@Composable
private fun MealDetailScreenContent(meal: Meal, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
    ) {
        AsyncImage(
            model = meal.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(bottom = 24.dp)
        )
        Text(
            text = "Ingredients:",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = meal.ingredients,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Description:",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = meal.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}




