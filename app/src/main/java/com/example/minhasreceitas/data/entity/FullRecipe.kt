package com.example.minhasreceitas.data.entity

import androidx.room.Embedded
import androidx.room.Relation


typealias  FullRecipeEntity = FullRecipe
//representar o relacionamento
data class FullRecipe (
    //pai - receita
    @Embedded val recipe: RecipeEntity,

    @Relation(
        //id da tabela pai
        parentColumn = "id",
        entityColumn = "idRecipe"
    )
    val ingredient: List<Ingredient>,

    @Relation(
        //id da tabela pai
        parentColumn = "id",
        entityColumn = "idRecipe"
    )
    val prepareMode: List <PrepareMode>
)