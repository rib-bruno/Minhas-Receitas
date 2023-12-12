package com.example.minhasreceitas.data.repository

import com.example.minhasreceitas.data.dao.RecipeDao
import com.example.minhasreceitas.data.entity.Ingredient
import com.example.minhasreceitas.data.mapper.toDomain
import com.example.minhasreceitas.data.mapper.toEntity
import com.example.minhasreceitas.domain.model.FullRecipeDomain
import com.example.minhasreceitas.domain.model.IngredientDomain
import com.example.minhasreceitas.domain.model.PrepareModeDomain
import com.example.minhasreceitas.domain.model.RecipeDomain
import com.example.minhasreceitas.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(private val dao: RecipeDao) : RecipeRepository {
    override suspend fun getAll(): Flow<List<RecipeDomain>>  = withContext(Dispatchers.IO) {
        dao.getAll().map {list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override suspend fun insert(recipe: RecipeDomain) = withContext(Dispatchers.IO) {
        //como vai inserir no banco de dados, o banco de dados não sabe o que é
        //um domínio, e sim uma entidade pertiente a esse banco de dados
        dao.insert(recipe.toEntity())
    }

    override suspend fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): FullRecipeDomain =
        withContext(Dispatchers.IO) {
            dao.getRecipeWithIngredientsAndPrepareMode(idRecipe).toDomain()
        }
    override suspend fun insertIngredient(ingredient: IngredientDomain) =
        withContext(Dispatchers.IO) {
            dao.insert(Ingredient(
                id = ingredient.id,
                name = ingredient.name,
                idRecipe = ingredient.idRecipe
            ))
           // dao.insert(ingredient.toEntity())
        }


    override suspend fun insertPrepareMode(prepareMode: PrepareModeDomain)  =
        withContext(Dispatchers.IO) {
            dao.insert(prepareMode.toEntity())
        }

    override suspend fun updateIngredient(ingredient: IngredientDomain) =
        withContext(Dispatchers.IO) {
            dao.updateIngredient(ingredient.toEntity())
        }

    override suspend fun updatePrepareMode(prepareMode: PrepareModeDomain) =
        withContext(Dispatchers.IO) {
            dao.updatePrepareMode(prepareMode.toEntity())
        }
}