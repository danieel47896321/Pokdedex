package com.example.pokedex.model

data class Pokemon(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_types: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
){
    fun getID(): String {
        var fullID = "#"
        fullID += when (id.toString().length) {
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