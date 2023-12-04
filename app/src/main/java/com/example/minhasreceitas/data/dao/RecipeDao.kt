package com.example.minhasreceitas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.minhasreceitas.data.entity.Recipe
import com.example.minhasreceitas.data.entity.RecipeEntity

//vai se comunicar com nossa entidade
@Dao
interface RecipeDao {
    @Query("Select * FROM recipe")
    fun getAll() : List<Recipe>

    @Insert
    fun insert(recipe: RecipeEntity)
}