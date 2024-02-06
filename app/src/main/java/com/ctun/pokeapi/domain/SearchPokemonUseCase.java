package com.ctun.pokeapi.domain;

import androidx.lifecycle.LiveData;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.PokemonList;

import javax.inject.Inject;

public class SearchPokemonUseCase {
    @Inject
    PokemonRepository repository;

    @Inject
    public SearchPokemonUseCase(){}

    public LiveData<PokemonList> searchPokemon(String name){
        return repository.searchPokemon(name);
    }
}
