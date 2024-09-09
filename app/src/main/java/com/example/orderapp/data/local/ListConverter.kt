package com.example.orderapp.data.local

import androidx.room.TypeConverter
import com.example.orderapp.data.model.Meal
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDateTime

class ListConverter {

    @TypeConverter
    fun fromMealTOString(meal: Meal):String{
        return Json.encodeToString(meal)
    }

    @TypeConverter
    fun fromStringTOMeal(string: String):String{
        return Json.decodeFromString(string)
    }

    @TypeConverter
    fun fromMealListTOString(mealList: List<Meal>):String{
        return Json.encodeToString(mealList)
    }

    @TypeConverter
    fun fromStringTOMealList(string: String):List<Meal>{
        return Json.decodeFromString(string)
    }

    @TypeConverter
    fun fromDateTimeTOString(localDateTime: LocalDateTime):String{
        return Json.encodeToString(localDateTime)
    }

    @TypeConverter
    fun fromStringTODateTime(string: String):LocalDateTime{
        return Json.decodeFromString(string)
    }
}