package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase


import androidx.compose.ui.text.font.emptyCacheFontFamilyResolver
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.DetalhePokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemonRepository

import javax.inject.Inject


class GetDetailPokemonUseCase @Inject() constructor(
    private val detalhePokemonRepository: DetalhePokemonRepository
){
    suspend operator fun invoke() : DetalhePokemon?{
        return try {
            detalhePokemonRepository.detalhePokemon()
        }catch (erroRecuperarDetalhePokemon: Exception){
            erroRecuperarDetalhePokemon.printStackTrace()
            return null
        }
    }
}

