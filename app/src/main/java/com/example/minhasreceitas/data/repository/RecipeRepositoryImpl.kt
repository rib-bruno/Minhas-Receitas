package com.example.minhasreceitas.data.repository

import com.example.minhasreceitas.data.dao.RecipeDao
import com.example.minhasreceitas.data.mapper.toDomain
import com.example.minhasreceitas.data.mapper.toEntity
import com.example.minhasreceitas.domain.model.RecipeDomain
import com.example.minhasreceitas.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(private val dao: RecipeDao) : RecipeRepository {
    override suspend fun getAll(): List<RecipeDomain> = withContext(Dispatchers.IO) {
        dao.getAll().map {
            it.toDomain()
        }
    }

    override suspend fun insert(recipe: RecipeDomain) = withContext(Dispatchers.IO) {
        //como vai inserir no banco de dados, o banco de dados não sabe o que é
        //um domínio, e sim uma entidade pertiente a esse banco de dados
        dao.insert(recipe.toEntity())
    }
}