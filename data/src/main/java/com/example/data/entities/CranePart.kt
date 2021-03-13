package com.example.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.converters.CranePartsConverter

@Entity(tableName = "crane_part_table")
data class CranePart(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "craneId") val craneId: Int,
    @ColumnInfo(name = "type") val type: String,
    @TypeConverters(CranePartsConverter::class) val craneParts: List<CraneParts>

)

data class CraneParts(
    val name: String,
    val filled: Boolean? = null,
    val openView: Boolean? = null,
    val pieces: List<CranePartPieces>
)

data class CranePartPieces(
    val name: String,
    val comment: String? = null,
    val filled: Boolean? = null,
    val satisfactory: Boolean? = null,
    val unsatisfactory: Boolean? = null
)
