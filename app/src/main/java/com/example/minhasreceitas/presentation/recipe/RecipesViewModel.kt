package com.example.minhasreceitas.presentation.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.minhasreceitas.data.db
import com.example.minhasreceitas.data.repository.RecipeRepositoryImpl
import com.example.minhasreceitas.domain.model.RecipeDomain
import com.example.minhasreceitas.domain.use_case.GetAllRecipesUseCase
import com.example.minhasreceitas.domain.use_case.InsertRecipeUseCase
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val insertRecipesUseCase: InsertRecipeUseCase
) : ViewModel() {

    val state : LiveData<RecipeState> = liveData {
        //emitir os eventos pra view
        emit(RecipeState.Loading)

        val state = try {
            //tentar recuperar as receitas
            val recipes = getAllRecipesUseCase()
            if (recipes.isEmpty()) {
                RecipeState.Empty
            } else {
                RecipeState.Success(recipes)
            }


        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            RecipeState.Error(exception.message.toString())
        }
        emit(state)
    }

    //informar um texto e a partir dele gravar no banco
    fun insert(name: String) = viewModelScope.launch {
        insertRecipesUseCase(RecipeDomain(name = name))
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            //injetar o repositorio pro viewmodel usar
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return RecipesViewModel(
                getAllRecipesUseCase = GetAllRecipesUseCase(repository),
                insertRecipesUseCase = InsertRecipeUseCase(repository)
            ) as T
        }
    }

}