package com.example.domain.usecases.crane_parts

import com.example.domain.entities.CranePartInfo
import com.example.domain.entities.CraneParts
import com.example.domain.repositories.CranePartRepository
import com.example.domain.usecases.BaseUseCase

class UpdateCraneByIdAndTypeUseCase(
    private val cranePartRepository: CranePartRepository
) : BaseUseCase<Unit, UpdateCraneByIdAndTypeUseCase.Params>() {

    override suspend fun run(params: Params) {
        cranePartRepository.updateCraneByIdAndType(
            params.craneId,
            params.craneType,
            params.craneParts
        )
    }

    data class Params(
        val craneId: Int,
        val craneType: String,
        val craneParts: List<CraneParts>
    )
}