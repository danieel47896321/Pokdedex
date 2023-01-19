package com.example.pokedex

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.adapter.PokemonAdapter
import com.example.pokedex.model.PokemonInfo
import com.example.pokedex.viewmodel.PokemonViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var title: TextView
    private lateinit var backIcon: ImageView
    private lateinit var recyclerView : RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var pokemonAdapter: PokemonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init() {
        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        pokemonAdapter = PokemonAdapter(pokemonViewModel.getList())
        title = findViewById<TextView>(R.id.title)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        backIcon = findViewById<ImageView>(R.id.backIcon)
        backIcon.visibility = View.GONE
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = pokemonAdapter
        title.text = resources.getString(R.string.PokemonList)
        getPokemonList()
        setScrollView()
    }
    private fun setScrollView() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!recyclerView.canScrollVertically(1)) {
                    if(pokemonViewModel.getCurrentPage() * pokemonViewModel.getPageSize() < pokemonViewModel.getTotal()) {
                        pokemonViewModel.increasePage()
                        getPokemonList()
                    }
                }
            }
        })
    }
    @Synchronized
    private fun getPokemonList() {
        pokemonViewModel.getPokemonList(pokemonViewModel.getPageSize(), pokemonViewModel.getCurrentPage() * pokemonViewModel.getPageSize()).observe(this) { pokemonList ->
            progressBar.visibility = View.GONE
            val currentSize = pokemonViewModel.getList().size
            pokemonViewModel.setTotal(pokemonList.count)
            if(pokemonList != null) {
                for(i in 0 until pokemonViewModel.getPageSize()) {
                    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + (i+1+currentSize) + ".png"
                    pokemonViewModel.getList().add(PokemonInfo(pokemonList.results[i].name, imageUrl,(i+1+currentSize).toString()))
                }
                 pokemonAdapter.notifyItemRangeInserted(currentSize, pokemonViewModel.getList().size)
            } else {
                pokemonAdapter.notifyItemRangeInserted(0, currentSize)
            }
        }
    }
}