package com.ctun.pokeapi.domain;

import androidx.lifecycle.LiveData;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.PokemonList;

import javax.inject.Inject;

public class GetPokemonListUseCase {

    @Inject
    PokemonRepository repository;

    @Inject
    public GetPokemonListUseCase(){
    }

    public LiveData<PokemonList> getAll(int offset){
        return  repository.getAll(offset);
    }

    public LiveData<PokemonList> refreshList(int offset){
        return repository.refreshList(offset);
    }

    public LiveData<PokemonList> nextPage(int offset){
        return repository.nextPage(offset);
    }

}