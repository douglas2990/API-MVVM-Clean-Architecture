package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.toDetailPokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.DetalhePokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemonRepository
import javax.inject.Inject


class DetailPokemonRepositoryImpl @Inject constructor(
    private val dummyAPI: DummyAPI,
    override val pokemonId: String,
): DetalhePokemonRepository {
    override suspend fun detalhePokemon(): DetalhePokemon? {
        try {
            val respostaDetailPokemon = dummyAPI.pokemonDittoDetail(pokemonId)
            if( respostaDetailPokemon.isSuccessful && respostaDetailPokemon.body() != null){
                val detailPokemonAPIDTO = respostaDetailPokemon.body()
                val pokemonDetail = detailPokemonAPIDTO

                if( pokemonDetail != null){

                    return pokemonDetail.toDetailPokemon()
                }
            }

        }catch (erroRecuperarDetailPokemon: Exception){
            erroRecuperarDetailPokemon.printStackTrace()
        }

        return null
    }

}



