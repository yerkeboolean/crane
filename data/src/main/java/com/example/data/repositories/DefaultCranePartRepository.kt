package com.example.data.repositories

import com.example.data.converters.CranePartsConverter
import com.example.data.daos.CranePartDao
import com.example.data.entities.CranePart
import com.example.data.entities.CranePartPieces
import com.example.domain.entities.CraneInfo
import com.example.domain.entities.CranePartInfo
import com.example.domain.entities.CraneParts
import com.example.domain.entities.CranePartsPieces
import com.example.domain.repositories.CranePartRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DefaultCranePartRepository(
    private val cranePartDao: CranePartDao
) : CranePartRepository {
    override suspend fun deleteAllCraneParts() {
        GlobalScope.launch {
            cranePartDao.deleteAllCraneParts()
        }.join()
    }

    override suspend fun getAllCraneParts(): List<CranePartInfo> {
        var list: List<CranePartInfo> = listOf()

        GlobalScope.launch {
            list = cranePartDao.getAllCraneParts().map {
                transformToCranePartInfo(it)
            }
        }.join()

        return list
    }


    override suspend fun getCraneById(craneId: Int): List<CranePartInfo> {
        var list: List<CranePartInfo> = listOf()

        GlobalScope.launch {
            list = cranePartDao.getCraneById(craneId).map {
                transformToCranePartInfo(it)
            }
        }.join()

        return list
    }

    override suspend fun updateCraneByIdAndType(
        craneId: Int,
        craneType: String,
        craneParts: List<CraneParts>
    ) {
        GlobalScope.launch {
            cranePartDao.updateCraneByIdAndType(
                craneId,
                craneType,
                craneParts.map { transformToCraneParts(it) }
            )
        }.join()

    }


    override suspend fun insert(cranePart: CranePartInfo) {
        GlobalScope.launch {
            cranePartDao.insert(transformToCranePart(cranePart))
        }.join()
    }

    private fun transformToCranePartInfo(it: CranePart): CranePartInfo {
        return CranePartInfo(
            id = it.craneId,
            type = it.type,
            craneParts = it.craneParts.map { craneParts ->
                CraneParts(
                    name = craneParts.name,
                    filled = craneParts.filled,
                    openView = craneParts.openView,
                    pieces = craneParts.pieces.map { cranePartPieces ->
                        CranePartsPieces(
                            name = cranePartPieces.name,
                            satisfactory = cranePartPieces.satisfactory,
                            unsatisfactory = cranePartPieces.unsatisfactory,
                            filled = cranePartPieces.filled,
                            comment = cranePartPieces.comment
                        )
                    }
                )
            }
        )
    }

    private fun transformToCranePart(it: CranePartInfo): CranePart {
        return CranePart(
            craneId = it.id,
            type = it.type,
            craneParts = it.craneParts.map { craneParts ->
                com.example.data.entities.CraneParts(
                    name = craneParts.name,
                    filled = craneParts.filled,
                    openView = craneParts.openView,
                    pieces = craneParts.pieces.map { cranePartPieces ->
                        CranePartPieces(
                            name = cranePartPieces.name,
                            satisfactory = cranePartPieces.satisfactory,
                            unsatisfactory = cranePartPieces.unsatisfactory,
                            filled = cranePartPieces.filled,
                            comment = cranePartPieces.comment
                        )
                    }
                )
            }
        )
    }

    private fun transformToCraneParts(it: CraneParts): com.example.data.entities.CraneParts {
        return com.example.data.entities.CraneParts(
            name = it.name,
            filled = it.filled,
            openView = it.openView,
            pieces = it.pieces.map { cranePartPieces ->
                CranePartPieces(
                    name = cranePartPieces.name,
                    satisfactory = cranePartPieces.satisfactory,
                    unsatisfactory = cranePartPieces.unsatisfactory,
                    filled = cranePartPieces.filled,
                    comment = cranePartPieces.comment
                )
            }
        )
    }
}