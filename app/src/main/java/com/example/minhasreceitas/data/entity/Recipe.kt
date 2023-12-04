package com.example.minhasreceitas.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//definição das nossas classes, das entidades do banco de dados -tabela, usando uma abstração
//da SQlite

typealias RecipeEntity = Recipe
@Entity
data class Recipe(
    //autoincrementar
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String
)