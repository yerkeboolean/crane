package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.converters.CranePartsConverter
import com.example.data.converters.QuestionConverter
import com.example.data.daos.CranePartDao
import com.example.data.daos.QuestionDao
import com.example.data.entities.CraneInfo
import com.example.data.entities.CranePart

@Database(
    entities = [
        CraneInfo::class,
        CranePart::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(QuestionConverter::class, CranePartsConverter::class)
abstract class DB : RoomDatabase() {

    abstract fun getQuestion(): QuestionDao
    abstract fun getCraneParts(): CranePartDao


    companion object {

        @Volatile
        private lateinit var INSTANCE: DB

        fun getDatabase(context: Context): DB {
            synchronized(DB::class.java) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    DB::class.java, "crane_db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }


            return INSTANCE
        }
    }
}