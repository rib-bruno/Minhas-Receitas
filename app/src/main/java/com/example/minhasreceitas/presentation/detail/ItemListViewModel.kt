package com.example.minhasreceitas.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.minhasreceitas.domain.model.IngredientDomain
import com.example.minhasreceitas.domain.model.PrepareModeDomain
import com.example.minhasreceitas.domain.use_case.GetRecipeWithIngredientAndPrepareModeUseCase
import com.example.minhasreceitas.domain.use_case.InsertIngredientsUseCase
import com.example.minhasreceitas.domain.use_case.InsertPrepareModeUseCase
import com.example.minhasreceitas.presentation.mapper.toModelIngredient
import com.example.minhasreceitas.presentation.mapper.toModelPrepareMode
import kotlinx.coroutines.launch

class ItemListViewModel(
    private val getRecipeWithIngredientsAndPrepareModeUseCase: GetRecipeWithIngredientAndPrepareModeUseCase,
    private val insertIngredientsUseCase: InsertIngredientsUseCase,
    private val insertPrepareModeUseCase: InsertPrepareModeUseCase
) : ViewModel() {

    fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): LiveData<ItemListState> = liveData {
        emit(ItemListState.Loading)
        val state = try {
            val fullRecipe = getRecipeWithIngredientsAndPrepareModeUseCase(idRecipe)
            ItemListState.Success(
                ingredients = fullRecipe.ingredients.toModelIngredient(),
                prepareMode = fullRecipe.prepareMode.toModelPrepareMode()
            )
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            ItemListState.Error(exception.message.toString())
        }
        emit(state)
    }

    fun insertIngredientsOrPrepareMode(
        name: String,
        idRecipe: Int,
        typeInsert: String
    ) = viewModelScope.launch {
        if (typeInsert == "INGREDIENT") {
            insertIngredientsUseCase(
                IngredientDomain(
                    name = name,
                    idRecipe = idRecipe
                )
            )
        } else {
            insertPrepareModeUseCase(
                PrepareModeDomain(
                    name = name,
                    idRecipe = idRecipe
                )
            )
        }
    }


    fun updateIngredients() {
        //TODO - realizar update dos ingredientes
    }

    fun removeIngredient() {
        //TODO - realizar a exclusão do ingredient
    }

    fun updatePrepareMode() {
        //TODO realizar o update do modo de preparo
    }

    fun removePrepareMode() {
        //TODO realizar a remoção do modo de preparo
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            //todo implementar aqui
        }
    }


}