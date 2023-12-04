package com.example.minhasreceitas.domain.use_case

import com.example.minhasreceitas.domain.model.RecipeDomain
import com.example.minhasreceitas.domain.repository.RecipeRepository

class InsertRecipeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeDomain) = repository.insert(recipe)
}