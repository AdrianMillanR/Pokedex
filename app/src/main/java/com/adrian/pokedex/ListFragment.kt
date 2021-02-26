package com.adrian.pokedex

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.ClassCastException

class ListFragment : Fragment() {

    interface PokemonSelectListener{
        fun onPokemonSelected(pokemon: Pokemon)
    }
    private  lateinit var pokemonSelectListener: PokemonSelectListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        pokemonSelectListener= try {
            context as PokemonSelectListener
        }catch (e: ClassCastException) {
            throw ClassCastException("$context must implement PokemonSelectListener")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)
        val recycler= view.findViewById<RecyclerView>(R.id.pokemon_recycler)
        recycler.layoutManager= LinearLayoutManager(requireActivity())
        val adapter= PokemonAdapter()
        recycler.adapter=adapter

        adapter.onItemClickLitener={
            pokemonSelectListener.onPokemonSelected(it)
        }

        val pokemonList= mutableListOf(
                Pokemon(1,"Bulbasaur",55,49, 19,45, Pokemon.PokemonType.GRASS,"https://cdn.bulbagarden.net/upload/thumb/1/19/Ash_Bulbasaur.png/1200px-Ash_Bulbasaur.png"),
                Pokemon(2,"Ivysaur",46,91, 23,23, Pokemon.PokemonType.GRASS,"https://static.wikia.nocookie.net/pokemon/images/b/b7/Shauna_Ivysaur.png/revision/latest?cb=20151220131657"),
                Pokemon(3,"Venuasaur",31,10, 45,25, Pokemon.PokemonType.GRASS,"https://cdn.bulbagarden.net/upload/thumb/5/54/Kukui_Venusaur.png/250px-Kukui_Venusaur.png"),
                Pokemon(4,"Charmander",15,18, 29,45, Pokemon.PokemonType.FIRE,"https://media.redadn.es/imagenes/animanga-5_331452.jpg"),
                Pokemon(5,"Charmeleon",25,65, 49,65, Pokemon.PokemonType.FIRE,"https://i.pinimg.com/originals/80/42/50/8042507726c9558b011c25c0ca4ecac8.png"),
                Pokemon(6,"Charizzard",45,34, 90,78, Pokemon.PokemonType.FIRE,"https://i0.wp.com/www.bitme.gg/wp-content/uploads/2019/10/%C2%BFCuantas-versiones-de-Charizard-existen-en-los-Videojuegos-de-Pokemon_-1.jpg?fit=1280%2C720&ssl=1"),
                Pokemon(7,"Squirtle",56,12, 32,56, Pokemon.PokemonType.WATER,"https://dlprivateserver.com/wp-content/uploads/2020/01/Pokemon-Espada-amp-Escudo-%C2%BFPuedes-obtener-Squirtle-contestado-780x470.jpg"),
                Pokemon(8,"warturtle",67,56, 67,26, Pokemon.PokemonType.WATER,"https://static.wikia.nocookie.net/pokemon/images/9/9a/May_Wartortle.png/revision/latest?cb=20150906033026"),
                Pokemon(9,"Blastoise",98,39, 78,74, Pokemon.PokemonType.WATER,"https://static.fandomspot.com/images/08/2243/00-featured-blastoise.jpg"),
                Pokemon(10,"Pikachu",16,34, 32,12, Pokemon.PokemonType.ELECTRIC,"https://imagen.nextn.es/wp-content/uploads/2018/11/1811-22-Pokemon-Pikachu.jpg?strip=all&lossy=1&ssl=1"),
        )
        adapter.submitList(pokemonList)

        return view
    }


}