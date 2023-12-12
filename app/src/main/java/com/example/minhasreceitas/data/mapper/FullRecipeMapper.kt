package com.example.minhasreceitas.data.mapper

import com.example.minhasreceitas.data.entity.FullRecipeEntity
import com.example.minhasreceitas.domain.model.FullRecipeDomain
import com.example.minhasreceitas.domain.model.IngredientDomain

fun FullRecipeEntity.toDomain() = FullRecipeDomain(
    recipe = recipe.toDomain(),
    ingredients = ingredient.map {
        IngredientDomain(
            id = it.id,
            name = it.name,
            idRecipe = it.idRecipe
        )
    },
    prepareMode = prepareMode.map {
        it.toDomain()
    }
)