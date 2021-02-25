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
                Pokemon(1,"Bulbasaur",55,49, 19,45, Pokemon.PokemonType.GRASS),
                Pokemon(2,"Ivysaur",46,91, 23,23, Pokemon.PokemonType.GRASS),
                Pokemon(3,"Venuasaur",31,10, 45,25, Pokemon.PokemonType.GRASS),
                Pokemon(4,"Charmander",15,18, 29,45, Pokemon.PokemonType.FIRE),
                Pokemon(5,"Charmeleon",25,65, 49,65, Pokemon.PokemonType.FIRE),
                Pokemon(6,"Charizzard",45,34, 90,78, Pokemon.PokemonType.FIRE),
                Pokemon(7,"Squirtle",56,12, 32,56, Pokemon.PokemonType.WATER),
                Pokemon(8,"warturtle",67,56, 67,26, Pokemon.PokemonType.WATER),
                Pokemon(9,"Blastoise",98,39, 78,74, Pokemon.PokemonType.WATER),
                Pokemon(10,"Pikachu",16,34, 32,12, Pokemon.PokemonType.ELECTRIC),
        )
        adapter.submitList(pokemonList)

        return view
    }


}