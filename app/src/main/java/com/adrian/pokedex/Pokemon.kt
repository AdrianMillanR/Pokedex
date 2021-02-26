package com.adrian.pokedex

data class Pokemon(val id: Long, val name:String, val hp:Int, val attack:Int, val defense:Int, val speed:Int, val type:PokemonType, val image:String) {

    enum class PokemonType{
        GRASS, FIRE, WATER, FIGTHER, ELECTRIC
    }
}