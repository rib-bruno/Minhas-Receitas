package com.example.minhasreceitas.presentation.detail

import com.example.minhasreceitas.presentation.model.ItemListModel

interface ItemListState {

    object Loading: ItemListState
    data class Success(
        val ingredients: List<ItemListModel>,
        val prepareMode: List<ItemListModel>
    ) : ItemListState

    data class Error(val message: String) : ItemListState
}