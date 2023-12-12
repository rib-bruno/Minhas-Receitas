package com.example.minhasreceitas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.minhasreceitas.data.entity.FullRecipeEntity
import com.example.minhasreceitas.data.entity.Ingredient
import com.example.minhasreceitas.data.entity.PrepareMode
import com.example.minhasreceitas.data.entity.Recipe
import com.example.minhasreceitas.data.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

//vai se comunicar com nossa entidade
@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): Flow<List<Recipe>>

    @Insert
    fun insert(recipe: Recipe)

    @Insert
    fun insert(ingredient: Ingredient)

    @Insert
    fun insert(prepareMode: PrepareMode)

    @Transaction
    @Query("SELECT * From recipe where id = :recipeId")
    fun getRecipeWithIngredientsAndPrepareMode(recipeId: Int): FullRecipeEntity

    @Update
    fun updateIngredient(ingredient: Ingredient)

    @Update
    fun updatePrepareMode(prepareMode: PrepareMode)
}