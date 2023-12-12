package com.example.minhasreceitas.domain.repository

import com.example.minhasreceitas.domain.model.FullRecipeDomain
import com.example.minhasreceitas.domain.model.IngredientDomain
import com.example.minhasreceitas.domain.model.PrepareModeDomain
import com.example.minhasreceitas.domain.model.RecipeDomain
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getAll(): Flow<List<RecipeDomain>>
    suspend fun insert(recipe: RecipeDomain)
    suspend fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): FullRecipeDomain
    suspend fun insertIngredient(ingredient: IngredientDomain)
    suspend fun insertPrepareMode(prepareMode: PrepareModeDomain)
    suspend fun updateIngredient(ingredient: IngredientDomain)
    suspend fun updatePrepareMode(prepareMode: PrepareModeDomain)
}