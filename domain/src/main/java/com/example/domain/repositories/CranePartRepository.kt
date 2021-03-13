package com.example.domain.repositories

import com.example.domain.entities.*

    interface CranePartRepository {
    suspend fun deleteAllCraneParts()
    suspend fun getAllCraneParts(): List<CranePartInfo>
    suspend fun getCraneById(craneId: Int): List<CranePartInfo>
    suspend fun updateCraneByIdAndType(
        craneId: Int,
        craneType: String,
        craneParts: List<CraneParts>
    )
    suspend fun insert(cranePart: CranePartInfo)
}