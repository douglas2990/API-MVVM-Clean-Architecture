package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.DetalhePokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetDetailPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailPokemonViewModel  @Inject constructor(
    private val detalhePokemonUseCase: GetDetailPokemonUseCase,
    private val detalhePokemonId: String
) :  ViewModel(){

    private val _resultados = MutableLiveData<DetalhePokemon?>()

    val detalhePokemon: LiveData<DetalhePokemon?>
        get() = _resultados

    init {
        recuperarPokemon(detalhePokemonId)
    }

    fun recuperarPokemon(detalhePokemonId: String){
        viewModelScope.launch {
            val detalhePokemon = detalhePokemonUseCase()
            _resultados.postValue( detalhePokemon )
        }
    }

}

