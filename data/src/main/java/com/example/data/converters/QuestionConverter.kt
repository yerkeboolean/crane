package com.example.data.converters

import androidx.room.TypeConverter
import com.example.data.entities.CraneInfoSubQuestions
import com.example.domain.entities.CraneInfoResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object QuestionConverter {
    private val gson = Gson()

    @JvmStatic
    @TypeConverter
    fun fromSubQuestions(data:List<CraneInfoSubQuestions>):String{
        return gson.toJson(data)
    }

    @JvmStatic
    @TypeConverter
    fun toSubQuestions(data:String):List<CraneInfoSubQuestions>{
        val listType = object : TypeToken<List<CraneInfoSubQuestions>>() {}.type
        return gson.fromJson(data,listType)

    }

}
