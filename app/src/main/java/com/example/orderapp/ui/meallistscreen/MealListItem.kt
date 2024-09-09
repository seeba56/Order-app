package com.example.orderapp.ui.meallistscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.orderapp.R
import com.example.orderapp.data.model.Meal

@Composable
fun MealListItem(
    meal: Meal,
    onMealItemClick: (Meal) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 8.dp)
        ) {
            AsyncImage(
                model = meal.image,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clickable { onMealItemClick.invoke(meal) },

                )
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onMealItemClick.invoke(meal) },
                verticalArrangement = Arrangement.SpaceBetween,

                ) {
                Text(text = meal.name, style = MaterialTheme.typography.labelLarge)
                Text(text = meal.price, style = MaterialTheme.typography.labelSmall)
            }
        }
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp)
    }

}

@Composable
@Preview
private fun MealListItemPreview() {
    val meal = Meal(
        name = "Pizza capriciosa",
        price = "12",
        ingredients = "",
        image = "",
        category = "",
        description = ""
    )
    MealListItem(meal = meal, {})
}