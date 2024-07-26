package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Type
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.ListTypeAdapterBinding

class ListTypeAdapter (private val list: List<Type>,
                       private var listenner: PokemonInterface? = null
)
    : RecyclerView.Adapter<ListTypeAdapter.ListTypeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListTypeViewHolder {

        return ListTypeViewHolder(
            ListTypeAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListTypeViewHolder, position: Int) {

        val type = list[position]
        holder.buttonType.text = type.type.name


    }

    class ListTypeViewHolder(binding: ListTypeAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var buttonType = binding.btnType


    }

    override fun getItemCount(): Int {
        return list.size
    }







}