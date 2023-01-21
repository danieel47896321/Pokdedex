package com.example.pokedex.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.PokemonPage
import com.example.pokedex.R
import com.example.pokedex.model.PokemonInfo

class PokemonAdapter(private var list: ArrayList<PokemonInfo>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_view, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        val pokemon = list[position]
        holder.textViewPokemonName.text = pokemon.getPokemonName()
        holder.textViewPokemonID.text = pokemon.getID()
        Glide.with(holder.itemView).load(pokemon.getImage()).into(holder.imageViewPokemonImage)
        holder.linearLayout.setOnClickListener {
            val intent = Intent(holder.itemView.context, PokemonPage::class.java)
            intent.putExtra("name", pokemon.name)
            holder.itemView.context.startActivity(intent)
            (holder.itemView.context as Activity).finish()
        }
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