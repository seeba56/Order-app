package com.example.orderapp.ui.orderscreen

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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.orderapp.R
import com.example.orderapp.data.model.Meal

@Composable
fun OrderedMealItem(
    meal: Meal,
    onAddToOrder: (Meal) -> Unit,
    onRemoveFromOrder: (Meal) -> Unit,
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
            Spacer(modifier = Modifier.weight(1F))
            IconButton(
                onClick = {
                    if (meal.isInOrder) {
                        meal.isInOrder = false

                        onRemoveFromOrder.invoke(meal)

                    } else {
                        meal.isInOrder = true

                        onAddToOrder.invoke(meal)
                    }
                }) {
                Icon(
                    painter = painterResource(
                        id = if (meal.isInOrder) {
                            R.drawable.baseline_shopping_cart_24
                        } else {
                            R.drawable.outline_shopping_cart_24
                        }
                    ),
                    contentDescription = null
                )
            }
        }
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp)
    }

}