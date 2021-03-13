package com.example.domain.usecases.crane_parts

import com.example.domain.entities.CraneInfo
import com.example.domain.entities.CranePartInfo
import com.example.domain.repositories.CranePartRepository
import com.example.domain.repositories.QuestionRepository
import com.example.domain.usecases.BaseUseCase

class CreateCranePartUseCase(
    private val cranePartRepository: CranePartRepository
) : BaseUseCase<Unit, CranePartInfo>() {

    override suspend fun run(params: CranePartInfo) {
        cranePartRepository.insert(params)
    }

}