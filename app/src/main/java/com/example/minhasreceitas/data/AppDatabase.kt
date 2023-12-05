package com.example.minhasreceitas.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.minhasreceitas.data.dao.RecipeDao
import com.example.minhasreceitas.data.entity.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    //definir o que se tem de dao
    abstract fun recipeDao(): RecipeDao
}