package com.example.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.model.Pokemon
import com.example.pokedex.model.PokemonInfo
import com.example.pokedex.viewmodel.PokemonViewModel

class PokemonPage : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var backIcon: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var pokemon: Pokemon
    private lateinit var name: String
    private lateinit var pokemonViewModel: PokemonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_page)
        init()
    }
    private fun init() {
        name = (intent.getSerializableExtra("name") as? String)!!
        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        title = findViewById<TextView>(R.id.title)
        backIcon = findViewById<ImageView>(R.id.backIcon)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        title.text = name
        buttonBackIcon()
        getPokemon()
    }
    private fun getPokemon() {
        pokemonViewModel.getPokemonInfo(name).observe(this) { POKEMON ->
            progressBar.visibility = View.GONE
            if (POKEMON != null) {
                Toast.makeText(this, POKEMON.name, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun buttonBackIcon() {
        backIcon.setOnClickListener {
            onBackPressed()
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}