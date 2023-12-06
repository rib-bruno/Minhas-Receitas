package com.example.minhasreceitas.presentation.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.minhasreceitas.R
import com.example.minhasreceitas.databinding.FragmentFirstBinding
import com.example.minhasreceitas.presentation.recipe.adapter.RecipeAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeFragment : Fragment() {

    //chamando o viewmodel
    private val viewModel: RecipesViewModel by viewModels {
        RecipesViewModel.Factory()
    }

    private lateinit var binding: FragmentFirstBinding
    //adapter pra fazer listagem dos itens
    private val adapter by lazy { RecipeAdapter () }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupAdapter()
        observeStates()

    }

    fun setupListeners(){
        binding.fabRecipe.setOnClickListener {
            //TODO show dialog
        }
    }

    fun setupAdapter() {
       binding.rvRecipes.adapter = adapter
    }

    //observador de estado
    private fun observeStates() {
        viewModel.state.observe(viewLifecycleOwner) {state ->
            when(state) {
                //como ja tem mapeado quais estados espera observar = preenchimento
                //sealed -> trabalhar com o escopo fechado de tudo que ele já espera
                RecipeState.Loading -> {
                    binding.pbLoading.isVisible = true
                }
                RecipeState.Empty -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.label_empty_recipes),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is RecipeState.Error -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                       state.message,
                        Toast.LENGTH_LONG
                    ).show()
                }

                is RecipeState.Success -> {
                    binding.pbLoading.isVisible = false
                    adapter.submitList(state.recipe)
                }
            }

        }
    }

}