package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.model.PokemonInfo
import com.example.pokedex.viewmodel.PokemonViewModel

class PokemonListPage : AppCompatActivity() {
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var backIcon: ImageView
    private lateinit var recyclerView : RecyclerView
    private lateinit var editTextUserSearch: EditText
    private lateinit var pokemonAdapter: PokemonAdapter
    private var list = ArrayList<PokemonInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_list_page)
        init()
    }
    private fun init() {
        list = (intent.getSerializableExtra("pokemonList") as? ArrayList<PokemonInfo>)!!
        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        pokemonViewModel.setList(list)
        pokemonAdapter = PokemonAdapter(list)
        editTextUserSearch = findViewById<EditText>(R.id.editTextUserSearch)
        backIcon = findViewById<ImageView>(R.id.backIcon)
        backIcon.visibility = View.GONE
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = pokemonAdapter
        setUserSearch()
    }
    private fun setUserSearch() {
        editTextUserSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                pokemonAdapter = PokemonAdapter(pokemonViewModel.userSearch(editTextUserSearch.text.toString()))
                recyclerView.adapter = pokemonAdapter
                pokemonAdapter.notifyDataSetChanged()
            }
            override fun afterTextChanged(s: Editable) {}
        })
    }
}