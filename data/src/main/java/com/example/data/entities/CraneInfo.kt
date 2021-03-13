package com.example.data.entities

import androidx.room.*
import com.example.data.converters.QuestionConverter

@Entity(tableName = "question_table")
data class CraneInfo(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "required") val required: Boolean,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "answer") val answer: String? = null,
    @TypeConverters(QuestionConverter::class) val subQuestions: List<CraneInfoSubQuestions>

)

data class CraneInfoSubQuestions(
    val answer: String? = null,
    val question: String
)