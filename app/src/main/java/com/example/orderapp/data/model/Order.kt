package com.example.orderapp.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mealList: List<Meal>,
    val time: Long
)