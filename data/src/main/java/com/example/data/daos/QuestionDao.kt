package com.example.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.CraneInfo

@Dao
abstract class QuestionDao {

    @Query("DELETE FROM question_table")
    abstract suspend fun deleteAllQuestions()

    @Query("SELECT * FROM question_table")
    abstract suspend fun getAllQuestions(): List<CraneInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(questions: CraneInfo)

}