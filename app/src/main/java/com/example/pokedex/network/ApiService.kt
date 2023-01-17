package com.example.pokedex.network

import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET ("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonList>

    @GET ("pokemon/{name}")
    fun getPokemonInfo(@Path("name") name: String): Call<Pokemon>
}