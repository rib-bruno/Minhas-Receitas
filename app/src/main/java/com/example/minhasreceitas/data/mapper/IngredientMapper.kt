package com.example.minhasreceitas.data.mapper

import com.example.minhasreceitas.data.entity.IngredientEntity
import com.example.minhasreceitas.domain.model.IngredientDomain

fun IngredientDomain.toEntity() = IngredientEntity(
    id = id,
    name = name,
    idRecipe = idRecipe
)

fun IngredientEntity.toDomain() = IngredientDomain(
    id = id,
    name = name,
    idRecipe = idRecipe
)