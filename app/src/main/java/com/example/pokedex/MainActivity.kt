package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.model.PokemonInfo
import com.example.pokedex.viewmodel.PokemonViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var pokemonViewModel: PokemonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init() {
        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        getPokemonList()
    }
    private fun getAllPokemon() {
        if(pokemonViewModel.getCurrentPage() * pokemonViewModel.getPageSize() < pokemonViewModel.getTotal()) {
            pokemonViewModel.increasePage()
            getPokemonList()
        } else {
            val intent = Intent(this, PokemonListPage::class.java)
            intent.putExtra("pokemonList", pokemonViewModel.getList())
            startActivity(intent)
            finish()
        }
    }
    private fun getPokemonList() {
        pokemonViewModel.getPokemonList(pokemonViewModel.getPageSize(), pokemonViewModel.getCurrentPage() * pokemonViewModel.getPageSize()).observe(this) { pokemonList ->
            pokemonViewModel.setTotal(pokemonList.count)
            for (i in 0 until pokemonList.results.size) {
                val id = pokemonList.results[i].url.split("/")
                pokemonViewModel.getList().add(PokemonInfo(pokemonList.results[i].name, id[6]))
            }
            getAllPokemon()
        }
    }
}