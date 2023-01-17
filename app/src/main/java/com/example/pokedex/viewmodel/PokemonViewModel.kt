package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonList
import com.example.pokedex.repository.PokemonRepository

class PokemonViewModel: ViewModel() {
    private var pokemonRepository = PokemonRepository()
    fun getPokemonList(limit: Int, offset: Int): LiveData<PokemonList> {
        return pokemonRepository.getPokemonList(limit, offset)
    }
    fun getPokemonInfo(name: String): LiveData<Pokemon> {
        return pokemonRepository.getPokemonInfo(name)
    }
}