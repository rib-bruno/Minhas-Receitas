package com.example.minhasreceitas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.minhasreceitas.data.entity.FullRecipeEntity
import com.example.minhasreceitas.data.entity.Ingredient
import com.example.minhasreceitas.data.entity.PrepareMode
import com.example.minhasreceitas.data.entity.Recipe
import com.example.minhasreceitas.data.entity.RecipeEntity

//vai se comunicar com nossa entidade
@Dao
interface RecipeDao {
    @Query("Select * FROM recipe")
    fun getAll() : List<Recipe>

    @Insert
    fun insert(recipe: RecipeEntity)

    @Insert
    fun insert(ingredient: Ingredient)

    @Insert
    fun insert(prepareMode: PrepareMode)

    @Transaction
    //id da receita vai ser passada por parâmetro
    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    fun getRecipeWithIngredientsAndPrepareMode(recipeId : Int) : FullRecipeEntity


}