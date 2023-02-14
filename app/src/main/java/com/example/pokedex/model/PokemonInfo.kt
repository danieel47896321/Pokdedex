package com.example.pokedex.model

import java.io.Serializable

class PokemonInfo(val name: String, private val id: String): Serializable {
    fun getID(): String {
        var fullID = "#"
        fullID += when (id.length) {
            3 -> "0$id"
            2 -> "00$id"
            1 -> "000$id"
            else -> id
        }
        return fullID
    }
    fun getPokemonName(): String {
        var pokemonName = ""
        if (name.isNotEmpty()) {
            pokemonName = if (name.length > 1) {
                name[0].uppercase() + name.substring(1, name.length)
            } else {
                name[0].uppercase()
            }
        }
        return pokemonName
    }
    fun getImage(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }
}