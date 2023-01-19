package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonInfo
import com.example.pokedex.model.PokemonList
import com.example.pokedex.repository.PokemonRepository

class PokemonViewModel: ViewModel() {
    private var list = ArrayList<PokemonInfo>()
    private var pageSize = 20
    private var currentPage = 0
    private var total = 0
    private var currentLocation = 0
    private var pokemonRepository = PokemonRepository()
    fun getPokemonList(limit: Int, offset: Int): LiveData<PokemonList> {
        return pokemonRepository.getPokemonList(limit, offset)
    }
    fun getPokemonInfo(name: String): LiveData<Pokemon> {
        return pokemonRepository.getPokemonInfo(name)
    }
    fun getList(): ArrayList<PokemonInfo> { return list }
    fun getPageSize(): Int { return pageSize }
    fun getCurrentPage(): Int { return currentPage }
    fun getTotal(): Int { return total }
    fun setTotal(totalNum: Int) { total = totalNum}
    fun increasePage() { currentPage++ }
    fun setCurrentLocation(loc: Int) { currentLocation = loc }
    fun getCurrentLocation(): Int { return currentLocation }
}