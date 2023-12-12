package com.example.minhasreceitas.presentation.mapper

import com.example.minhasreceitas.domain.model.IngredientDomain
import com.example.minhasreceitas.domain.model.PrepareModeDomain
import com.example.minhasreceitas.presentation.model.ItemListModel


fun IngredientDomain.toModel() = ItemListModel(
    id = id,
    name = name
)

fun List<IngredientDomain>.toModelIngredient() = map {
    it.toModel()
}

fun PrepareModeDomain.toModel() = ItemListModel(
    id = id,
    name = name
)

fun List<PrepareModeDomain>.toModelPrepareMode() = map {
    it.toModel()
}