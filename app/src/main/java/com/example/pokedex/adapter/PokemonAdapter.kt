package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.model.PokemonInfo

class PokemonAdapter(private var list: ArrayList<PokemonInfo>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_view, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        holder.textViewPokemonName.text = getPokemonName(list[position].pokemonName)
        holder.textViewPokemonID.text = getPokemonId(list[position].pokemonId)
        Glide.with(holder.itemView).load(list[position].imageUrl).into(holder.imageViewPokemonImage)
        holder.linearLayout.setOnClickListener{

        }
    }
    private fun getPokemonName(pokemonName: String): String {
        return pokemonName[0].uppercase() + pokemonName.substring(1, pokemonName.length)
    }
    private fun getPokemonId(pokemonId: String): String {
        var id = "#"
        if(pokemonId.length == 1)
            id += "000$pokemonId"
        else if(pokemonId.length == 2)
            id += "00$pokemonId"
        else if(pokemonId.length == 3)
            id += "0$pokemonId"
        else
            id += pokemonId
        return id
    }
    override fun getItemCount(): Int { return list.size }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewPokemonName: TextView
        var textViewPokemonID: TextView
        var imageViewPokemonImage: ImageView
        var linearLayout : LinearLayout
        init {
            textViewPokemonName = view.findViewById(R.id.textViewPokemonName)
            textViewPokemonID = view.findViewById(R.id.textViewPokemonID)
            imageViewPokemonImage = view.findViewById(R.id.imageViewPokemonImage)
            linearLayout = view.findViewById(R.id.linearLayout)
        }
    }
}