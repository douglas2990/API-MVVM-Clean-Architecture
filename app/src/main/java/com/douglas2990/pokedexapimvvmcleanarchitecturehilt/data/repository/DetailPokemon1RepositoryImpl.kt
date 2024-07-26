package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.DetailPokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.toResult
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemon1Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class DetailPokemon1RepositoryImpl @Inject constructor(
    private val pokemonAPI: DummyAPI,
    //private val context: Context
) : DetalhePokemon1Repository{

    override suspend fun recuperarPokemons(): List<DetalhePokemon1> {
        try {
            val resposta = pokemonAPI.getPokemon(2000,0)

            if( resposta.isSuccessful && resposta.body() != null){
                val resultAPIDTO = resposta.body()
                val listResult = resultAPIDTO?.results
                if( listResult != null ){

                    return listResult.map { result ->
                                val respostaPokemonDetail =
                                    pokemonAPI.pokemonDetail(result.name)
                                val pokemonDetail = respostaPokemonDetail.body()!!

                                DetalhePokemon1(
                                    id = pokemonDetail.id,
                                    nome = pokemonDetail.name,
                                    esprites = pokemonDetail.sprites,
                                    tipos = pokemonDetail.types
                                )

                    }

                }

            }else{
                Log.i("lista_usuarios", "${resposta.message()}")
            }
        }catch (erroRecuperarListaResult: Exception){
            erroRecuperarListaResult.printStackTrace()
        }

        return emptyList()
    }



}