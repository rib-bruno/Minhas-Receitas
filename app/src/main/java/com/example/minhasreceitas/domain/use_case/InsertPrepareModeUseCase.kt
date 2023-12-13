package com.example.minhasreceitas.domain.use_case

import com.example.minhasreceitas.domain.model.PrepareModeDomain
import com.example.minhasreceitas.domain.repository.RecipeRepository

class InsertPrepareModeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke (prepareMode: PrepareModeDomain) {
        repository.insertPrepareMode(prepareMode)
    }
}