package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.DetalhePokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.Resultado


interface DetalhePokemonRepository {
    //val pokemonId: String

    suspend fun detalhePokemon(pokemonId: String) : DetalhePokemon?
    //suspend fun detalhePokemon() : DetalhePokemon?
}

