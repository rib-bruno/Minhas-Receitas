package com.example.minhasreceitas.presentation.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.minhasreceitas.R
import com.example.minhasreceitas.databinding.FragmentFirstBinding
import com.example.minhasreceitas.presentation.recipe.adapter.RecipeAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeFragment : Fragment() {

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

    }

    fun setupListeners(){
        binding.fabRecipe.setOnClickListener {
            //TODO show dialog
        }
    }

    fun setupAdapter() {
       binding.rvRecipes.adapter = adapter
    }

}