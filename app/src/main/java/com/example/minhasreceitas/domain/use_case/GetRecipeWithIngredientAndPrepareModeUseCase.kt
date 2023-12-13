package com.example.minhasreceitas.domain.use_case

import com.example.minhasreceitas.domain.repository.RecipeRepository

class GetRecipeWithIngredientAndPrepareModeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(idRecipe: Int) =
        repository.getRecipeWithIngredientsAndPrepareMode(idRecipe)


}