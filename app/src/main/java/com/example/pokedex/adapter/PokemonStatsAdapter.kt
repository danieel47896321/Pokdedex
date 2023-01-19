package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.Stat
import java.security.AccessController.getContext


class PokemonStatsAdapter(var list: ArrayList<Stat>) : RecyclerView.Adapter<PokemonStatsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonStatsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.stat_view, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: PokemonStatsAdapter.ViewHolder, position: Int) {
        holder.textViewStatName.text = getStatName(holder, list[position].stat.name)
        holder.textViewValue.text = list[position].base_stat.toString()
        holder.cardViewCurrent.layoutParams = FrameLayout.LayoutParams(1200 - (1200 - list[position].base_stat * 10) , 120)
    }
    private fun getStatName(holder: ViewHolder, stat: String): String {
        var shortStat = ""
        when (stat) {
            "hp" -> shortStat = holder.itemView.context.getString(R.string.Hp)
            "attack" -> shortStat = holder.itemView.context.getString(R.string.Att)
            "defense" -> shortStat = holder.itemView.context.getString(R.string.Def)
            "special-attack" -> shortStat = holder.itemView.context.getString(R.string.SpAtt)
            "special-defense" -> shortStat = holder.itemView.context.getString(R.string.SpDef)
            "speed" -> shortStat = holder.itemView.context.getString(R.string.Speed)
        }
        return shortStat
    }
    override fun getItemCount(): Int { return list.size }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textViewStatName: TextView
        var textViewValue: TextView
        var cardViewMax: CardView
        var cardViewCurrent: CardView
        init {
            textViewStatName = view.findViewById(R.id.textViewStatName)
            textViewValue = view.findViewById(R.id.textViewValue)
            cardViewMax = view.findViewById(R.id.cardViewMax)
            cardViewCurrent = view.findViewById(R.id.cardViewCurrent)
        }
    }
}