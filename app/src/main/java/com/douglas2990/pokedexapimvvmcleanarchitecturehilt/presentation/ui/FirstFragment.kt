package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentFirstBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListPokemonAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val pokemonListViewModel by viewModels<PokemonListViewModel>()

    private val firstListener = object : ListPokemonAdapter.PokemonInterface{
        override fun onClick(pokemonId: String){
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundleOf("id" to pokemonId))

        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.    https://pokeapi.co/api/v2/pokemon?offset=0&limit=2000
    private val binding get() = _binding !!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFirst.layoutManager = LinearLayoutManager(context)


        pokemonListViewModel.listaPokemon.observe(viewLifecycleOwner){ resultPokemon->
            binding.firstProgress.isVisible = view.isInvisible

            binding.recyclerFirst.adapter = ListPokemonAdapter(resultPokemon, firstListener)
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun teste(){
        pokemonListViewModel.listaPokemon.observe(viewLifecycleOwner){ resultPokemon->
            var listaResultado = ""
            resultPokemon.forEach { pokemon ->
                val nome = pokemon.nome
                val numero = pokemon.urlDaApi.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "")

                listaResultado +=  "+) $nome - $numero -  \n"
            }
            //binding.textView.text = listaResultado
        }
    }

    /*
    fun teste2(){
        pokemonListViewModel.listaPokemon.observe(viewLifecycleOwner)
        { state ->
            binding.firstProgress.isVisible = state is ListResultState.Loading
            binding.recyclerFirst.isVisible = state is ListResultState.Success

            when (state){
                is ListResultState.Loading -> {}
                is ListResultState.SuccessResultado -> {
                    binding.recyclerFirst.adapter = ListPokemonAdapter(state.data, firstListener)
                }
                is ListResultState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
            }

        }
    }

     */
}