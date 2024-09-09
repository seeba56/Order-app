package com.example.orderapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerialName("Name")
    val name:String,
    @SerialName("Ingredients")
    val ingredients:String,
    @SerialName("Price")
    val price:String,
    @SerialName("Image")
    val image:String,
    @SerialName("Category")
    val category:String,
    @SerialName("Description")
    val description:String,
    var isInOrder:Boolean = false
)
