package com.example.minhasreceitas.domain.use_case

import com.example.minhasreceitas.domain.repository.RecipeRepository

class GetAllRecipesUseCase constructor(
    private val repository: RecipeRepository
) {
    //chamar de forma menos verbosa
    suspend operator fun invoke() = repository.getAll()


}