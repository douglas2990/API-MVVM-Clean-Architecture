package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetDetailPokemonUseCase


//class DetailPokemonFactory (private val pokemonId: String) : ViewModelProvider.Factory {
class DetailPokemonFactory (private val getUseCasepokemon: GetDetailPokemonUseCase,
                            private val pokemonId: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailPokemonViewModel(getUseCasepokemon,pokemonId) as T
    }

}

