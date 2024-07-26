package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentSecondBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail.DetailPokemonFactory
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail.DetailPokemonViewModel
import coil.load
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null





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

    @SuppressLint("FragmentLiveDataObserve", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //binding.progressBarHp.progress = 200
        binding.progressBarHp.incrementProgressBy(200)
        binding.progressBarHp.max = 400



        detailPokemonViewModel.detalhePokemon.observe(viewLifecycleOwner){ resultPokemon->
            binding.detailNamePokemon.text = "#" + resultPokemon?.id.toString().padStart(3,'0') + " " + resultPokemon?.nome.toString()
            binding.detailPokemon.load(resultPokemon?.esprites?.other?.home?.front_default)
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