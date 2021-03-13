package com.example.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.entities.CranePart
import com.example.data.entities.CraneParts

@Dao
abstract class CranePartDao {

    @Query("DELETE FROM crane_part_table")
    abstract suspend fun deleteAllCraneParts()

    @Query("SELECT * FROM crane_part_table")
    abstract suspend fun getAllCraneParts(): List<CranePart>

    @Query("SELECT * FROM crane_part_table WHERE craneId = :craneId")
    abstract suspend fun getCraneById(craneId: Int): List<CranePart>

    @Query("UPDATE crane_part_table SET craneParts = :craneParts  WHERE craneId = :craneId and type = :craneType")
    abstract suspend fun updateCraneByIdAndType(
        craneId: Int,
        craneType: String,
        craneParts: List<CraneParts>
    )

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(cranePart: CranePart)


}