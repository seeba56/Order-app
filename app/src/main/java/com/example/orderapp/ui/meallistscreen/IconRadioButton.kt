package com.example.orderapp.ui.meallistscreen

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun IconRadioButton(
    isSelected: Boolean,
    onClick: (String) -> Unit,
    value: String,
    resDrawable: Int
) {
    IconButton(onClick = { onClick.invoke(value) }) {
        Icon(
            painter = painterResource(id = resDrawable),
            contentDescription = null,
            tint = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )
    }

}