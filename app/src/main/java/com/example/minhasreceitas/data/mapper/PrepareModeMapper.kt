package com.example.minhasreceitas.data.mapper

import com.example.minhasreceitas.data.entity.PrepareModeEntity
import com.example.minhasreceitas.domain.model.PrepareModeDomain

fun PrepareModeDomain.toEntity() = PrepareModeEntity(
    id = id,
    name = name,
    idRecipe = idRecipe
)

fun PrepareModeEntity.toDomain() = PrepareModeDomain(
    id = id,
    name = name,
    idRecipe = idRecipe
)