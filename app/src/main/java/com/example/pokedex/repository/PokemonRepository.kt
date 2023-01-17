package com.example.pokedex.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonList
import com.example.pokedex.network.ApiClient
import com.example.pokedex.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository {
    private var apiService: ApiService = ApiClient.getRetrofit()?.create(ApiService::class.java)!!
    fun getPokemonList(limit: Int, offset: Int): LiveData<PokemonList> {
        val data: MutableLiveData<PokemonList> = MutableLiveData()
        apiService.getPokemonList(limit, offset).enqueue(object: Callback<PokemonList> {
            override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                data.value = response.body()
            }
            override fun onFailure(call: Call<PokemonList?>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
    fun getPokemonInfo(name: String): LiveData<Pokemon> {
        val data: MutableLiveData<Pokemon> = MutableLiveData()
        apiService.getPokemonInfo(name).enqueue(object: Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                data.value = response.body()
            }
            override fun onFailure(call: Call<Pokemon?>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}