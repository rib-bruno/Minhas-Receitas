package com.example.minhasreceitas.presentation.recipe.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.minhasreceitas.databinding.ItemRecipeBinding
import com.example.minhasreceitas.domain.model.RecipeDomain

class RecipeAdapter : ListAdapter<RecipeDomain, RecipeAdapter.ViewHolder>(DiffCallBack()) {

    //passar para cliclar no item da lista
    var click: (RecipeDomain) -> Unit = {}

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ItemRecipeBinding.inflate(inflater, parent, false)
//
//        return ViewHolder(binding)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeDomain) {
            //conseguir atribuir todas as informacoes do banco de dados
            binding.tvTitle.text = item.name
        }

    }
}

//método que consegue dar uma certa performance para nossa recyclerview.
//insersão de itens que não tem na nossa lista, quando ela começar a ser reciclada,
// colocar só quem está faltando
class DiffCallBack : DiffUtil.ItemCallback<RecipeDomain>() {
    override fun areItemsTheSame(oldItem: RecipeDomain, newItem: RecipeDomain) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: RecipeDomain, newItem: RecipeDomain) =
        oldItem.id == newItem.id
}