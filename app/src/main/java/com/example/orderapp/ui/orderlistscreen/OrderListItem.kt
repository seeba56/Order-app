package com.example.orderapp.ui.orderlistscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.orderapp.data.model.Order
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderListItem(order: Order) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                order.mealList.forEach {
                    Text(text = it.name, modifier = Modifier.padding(bottom = 8.dp))
                }
            }
            Text(
                text = LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(order.time),
                    ZoneId.systemDefault()
                ).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
            )

        }
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp)
    }

}