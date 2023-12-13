package com.example.minhasreceitas.domain.use_case

import com.example.minhasreceitas.domain.model.IngredientDomain
import com.example.minhasreceitas.domain.repository.RecipeRepository

class InsertIngredientsUseCase constructor (
    private val repository: RecipeRepository
) {
    suspend operator fun invoke (ingredientDomain: IngredientDomain) {
        repository.insertIngredient(ingredientDomain)
    }

}