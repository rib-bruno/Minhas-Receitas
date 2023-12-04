package com.example.minhasreceitas.data.mapper

import com.example.minhasreceitas.data.entity.RecipeEntity
import com.example.minhasreceitas.domain.model.RecipeDomain

fun RecipeDomain.toEntity() = RecipeEntity(
    id = id,
    name = name
)

fun RecipeEntity.toDomain() = RecipeDomain(
    id = id,
    name = name
)