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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val insertRecipesUseCase: InsertRecipeUseCase
) : ViewModel() {

   private val _state = MutableSharedFlow<RecipeState>()
    val state : SharedFlow<RecipeState> = _state

    init {
        getAllRecipes()
    }

    //pegar as chamadas do flow
    private fun getAllRecipes() = viewModelScope.launch {
        getAllRecipesUseCase()
            //despachado na main
            .flowOn(Dispatchers.Main)
            //quando o fluxo iniciar
            .onStart {
                _state.emit(RecipeState.Loading)
            }.catch {
                _state.emit(RecipeState.Error("erro"))
            }.collect{recipes ->
                if (recipes.isEmpty()) {
                    _state.emit(RecipeState.Empty)
                } else {
                    _state.emit(RecipeState.Success(recipes))
                }

            }
    }

    //informar um texto e a partir dele gravar no banco
    fun insert(name: String) = viewModelScope.launch {
        insertRecipesUseCase(RecipeDomain(name = name, prepareTime = "45 min"))
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