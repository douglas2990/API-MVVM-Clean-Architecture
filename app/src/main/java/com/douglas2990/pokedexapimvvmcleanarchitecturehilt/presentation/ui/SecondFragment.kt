package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentSecondBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.DetalhePokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemonRepository
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetDetailPokemonUseCase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListPokemonAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail.DetailPokemonFactory
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail.DetailPokemonViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null




    private val viewModel by viewModels<DetailPokemonViewModel>{
        DetailPokemonFactory( arguments?.getString("id","") ?: "")
        //DetailPokemonFactory(GetDetailPokemonUseCase)
    }

    private val detailPokemonViewModel by viewModels<DetailPokemonViewModel>()




    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //binding.progressBarHp.progress = 200
        binding.progressBarHp.incrementProgressBy(200)
        binding.progressBarHp.max = 400



        detailPokemonViewModel.detalhePokemon.observe(viewLifecycleOwner){ resultPokemon->
            binding.detailNamePokemon.text = resultPokemon?.nome.toString()
        }






    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onStart() {
        super.onStart()
        detailPokemonViewModel.recuperarPokemon(arguments?.getString("id","") ?: "")
    }


}