package com.example.data.converters

import androidx.room.TypeConverter
import com.example.data.entities.CraneParts
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CranePartsConverter {
    private val gson = Gson()

    @JvmStatic
    @TypeConverter
    fun fromCraneParts(data:List<CraneParts>):String{
        return gson.toJson(data)
    }

    @JvmStatic
    @TypeConverter
    fun toCraneParts(data:String):List<CraneParts>{
        val listType = object : TypeToken<List<CraneParts>>() {}.type
        return gson.fromJson(data,listType)

    }
}