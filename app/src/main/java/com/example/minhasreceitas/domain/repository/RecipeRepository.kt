package com.example.minhasreceitas.domain.repository

import com.example.minhasreceitas.domain.model.RecipeDomain

interface RecipeRepository {
    suspend fun getAll(): List<RecipeDomain>
    suspend fun insert(recipe: RecipeDomain)
}