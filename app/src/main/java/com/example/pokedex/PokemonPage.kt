package com.example.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.adapter.PokemonStatsAdapter
import com.example.pokedex.model.Stat
import com.example.pokedex.model.Type
import com.example.pokedex.viewmodel.PokemonViewModel

class PokemonPage : AppCompatActivity() {
    private lateinit var title: TextView
    private lateinit var textViewPokemonID: TextView
    private lateinit var textViewPokemonName: TextView
    private lateinit var textViewPokemonType1: TextView
    private lateinit var textViewPokemonType2: TextView
    private lateinit var textViewPokemonWeight: TextView
    private lateinit var textViewPokemonHeight: TextView
    private lateinit var cardViewPokemonType1: CardView
    private lateinit var cardViewPokemonType2: CardView
    private lateinit var backIcon: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var imageViewPokemonImage: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonStatsAdapter: PokemonStatsAdapter
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
        pokemonStatsAdapter = PokemonStatsAdapter(pokemonViewModel.getListStats())
        title = findViewById<TextView>(R.id.title)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        textViewPokemonID = findViewById<TextView>(R.id.textViewPokemonID)
        textViewPokemonName = findViewById<TextView>(R.id.textViewPokemonName)
        textViewPokemonType1 = findViewById<TextView>(R.id.textViewPokemonType1)
        textViewPokemonType2 = findViewById<TextView>(R.id.textViewPokemonType2)
        textViewPokemonWeight = findViewById<TextView>(R.id.textViewPokemonWeight)
        textViewPokemonHeight = findViewById<TextView>(R.id.textViewPokemonHeight)
        cardViewPokemonType1 = findViewById<CardView>(R.id.cardViewPokemonType1)
        cardViewPokemonType2 = findViewById<CardView>(R.id.cardViewPokemonType2)
        backIcon = findViewById<ImageView>(R.id.backIcon)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        imageViewPokemonImage = findViewById<ImageView>(R.id.imageViewPokemonImage)
        recyclerView.adapter = pokemonStatsAdapter
        buttonBackIcon()
        getPokemon()
    }
    private fun getPokemon() {
        pokemonViewModel.getPokemonInfo(name).observe(this) { pokemon ->
            if (pokemon != null) {
                progressBar.visibility = View.GONE
                title.text = pokemon.getPokemonName()
                Glide.with(this).load(pokemon.getImage()).into(imageViewPokemonImage)
                textViewPokemonID.text = pokemon.getID()
                textViewPokemonName.text = pokemon.getPokemonName()
                setPokemonTypes(pokemon.types.size, pokemon.types)
                textViewPokemonWeight.text = "${pokemon.weight.toFloat()/10} ${getString(R.string.Kg)}"
                textViewPokemonHeight.text = "${pokemon.height.toFloat()/10} ${getString(R.string.Meter)}"
                for(stat in pokemon.stats) {
                    pokemonViewModel.getListStats().add(stat)
                }
                pokemonStatsAdapter.notifyItemChanged(0,pokemon.stats.size)
            }
        }
    }
    private fun setPokemonTypes(size: Int, types: List<Type>) {
        when (size){
            1 -> {
                cardViewPokemonType2.visibility = View.GONE
                textViewPokemonType1.text = getType(types[0].type.name)
                setTypeBackground(textViewPokemonType1, types[0].type.name)
            }
            2 -> {
                textViewPokemonType1.text = getType(types[0].type.name)
                textViewPokemonType2.text = getType(types[1].type.name)
                setTypeBackground(textViewPokemonType1, types[0].type.name)
                setTypeBackground(textViewPokemonType2, types[1].type.name)
            }
        }
    }
    private fun setTypeBackground(textViewPokemonType1: TextView, type: String) {
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
    private fun getType(pokemonType: String): String {
        val type: String
        when (pokemonType) {
            "grass" -> type = getString(R.string.Grass)
            "water" -> type = getString(R.string.Water)
            "fire" -> type = getString(R.string.Fire)
            "electric" -> type = getString(R.string.Electric)
            "poison" -> type = getString(R.string.Poison)
            "psychic" -> type = getString(R.string.Psychic)
            "ice" -> type = getString(R.string.Ice)
            "flying" -> type = getString(R.string.Flying)
            "rock" -> type = getString(R.string.Rock)
            "bug" -> type = getString(R.string.Bug)
            "steel" -> type = getString(R.string.Steel)
            "normal" -> type = getString(R.string.Normal)
            "fighting" -> type = getString(R.string.Fighting)
            "ghost" -> type = getString(R.string.Ghost)
            "dark" -> type = getString(R.string.Dark)
            "ground" -> type = getString(R.string.Ground)
            "dragon" -> type = getString(R.string.Dragon)
            "fairy" -> type = getString(R.string.Fairy)
            else -> type = getString(R.string.Normal)
        }
        return type
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