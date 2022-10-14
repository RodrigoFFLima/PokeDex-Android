package com.example.pokedex_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_android.R
import com.example.pokedex_android.api.PokemonRepository
import com.example.pokedex_android.api.model.PokemonsApiResult
import com.example.pokedex_android.domain.Pokemon
import com.example.pokedex_android.domain.PokemonType
import com.example.pokedex_android.viewmodel.PokemonViewModel
import com.example.pokedex_android.viewmodel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    val viewModel by lazy{
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvPokemons)
        viewModel.pokemons.observe(this, Observer{
            loadRecycleView(it)
        })
    }

    private fun loadRecycleView(pokemons: List<Pokemon?>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PokemonAdapter(pokemons)
    }

}