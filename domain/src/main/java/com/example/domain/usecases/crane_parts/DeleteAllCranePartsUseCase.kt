package com.example.domain.usecases.crane_parts

import com.example.domain.entities.CranePartInfo
import com.example.domain.repositories.CranePartRepository
import com.example.domain.usecases.BaseUseCase


class DeleteAllCranePartsUseCase(
    private val cranePartRepository: CranePartRepository
) : BaseUseCase<Unit, Unit>() {

    override suspend fun run(params: Unit) {
        cranePartRepository.deleteAllCraneParts()
    }

}