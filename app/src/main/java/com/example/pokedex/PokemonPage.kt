package com.example.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokedex.model.Type
import com.example.pokedex.viewmodel.PokemonViewModel

class PokemonPage : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var textViewPokemonID: TextView
    private lateinit var textViewPokemonName: TextView
    private lateinit var textViewPokemonType1: TextView
    private lateinit var textViewPokemonType2: TextView
    private lateinit var backIcon: ImageView
    private lateinit var imageViewPokemonImage: ImageView
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
        textViewPokemonID = findViewById<TextView>(R.id.textViewPokemonID)
        textViewPokemonName = findViewById<TextView>(R.id.textViewPokemonName)
        textViewPokemonType1 = findViewById<TextView>(R.id.textViewPokemonType1)
        textViewPokemonType2 = findViewById<TextView>(R.id.textViewPokemonType2)
        backIcon = findViewById<ImageView>(R.id.backIcon)
        imageViewPokemonImage = findViewById<ImageView>(R.id.imageViewPokemonImage)
        title.text = getNameUppercaseFirstChar(name)
        buttonBackIcon()
        getPokemon()
    }
    private fun getPokemon() {
        pokemonViewModel.getPokemonInfo(name).observe(this) { pokemon ->
            if (pokemon != null) {
                val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + +pokemon.id + ".png"
                Glide.with(this).load(imageUrl).into(imageViewPokemonImage)
                textViewPokemonID.text = getPokemonId(pokemon.id.toString())
                textViewPokemonName.text = getNameUppercaseFirstChar(pokemon.name)
                setPokemonTypes(pokemon.types.size, pokemon.types)



            }
        }
    }

    private fun setPokemonTypes(size: Int, types: List<Type>) {
        when (size){
            1 -> {
                textViewPokemonType2.visibility = View.GONE
                textViewPokemonType1.text = getNameUppercaseFirstChar(types[0].type.name)
                setTypeBackground(textViewPokemonType1, types[0].type.name)
            }
            2 -> {
                textViewPokemonType1.text = getNameUppercaseFirstChar(types[0].type.name)
                textViewPokemonType2.text = getNameUppercaseFirstChar(types[1].type.name)
                setTypeBackground(textViewPokemonType1, types[0].type.name)
                setTypeBackground(textViewPokemonType2, types[1].type.name)
            }
        }
    }

    private fun setTypeBackground(textViewPokemonType1: TextView, type: String, ) {
        when (type) {
            "grass" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.grass)
            "water" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.water)
            "fire" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.fire)
            "electric" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.electric)
            "poison" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.poison)
            "psychic" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.psychic)
            "ice" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.ice)
            "flying" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.flying)
            "rock" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.rock)
            "bug" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.bug)
            "steel" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.steel)
            "normal" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.normal)
            "fighting" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.fighting)
            "ghost" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.ghost)
            "dark" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.dark)
            "ground" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.ground)
            "dragon" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.dragon)
            "fairy" -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.fairy)
            else -> textViewPokemonType1.background = ContextCompat.getDrawable(this, R.color.normal)
        }
    }

    private fun getPokemonId(pokemonId: String): String {
        var id = "#"
        id += when (pokemonId.length) {
            3 -> "0$pokemonId"
            2 -> "00$pokemonId"
            1 -> "000$pokemonId"
            else -> pokemonId
        }
        return id
    }
    private fun getNameUppercaseFirstChar(pokemonName: String): String {
        var name = ""
        if (pokemonName.isNotEmpty()) {
            name = if (pokemonName.length > 1) {
                pokemonName[0].uppercase() + pokemonName.substring(1, pokemonName.length)
            } else {
                pokemonName[0].uppercase()
            }
        }
        return name
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