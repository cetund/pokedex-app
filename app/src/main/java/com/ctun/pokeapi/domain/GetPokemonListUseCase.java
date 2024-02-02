package com.ctun.pokeapi.domain;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.PokemonList;
import com.ctun.pokeapi.utils.ApiServiceCallback;

import javax.inject.Inject;

public class GetPokemonListUseCase {
    @Inject
    PokemonRepository repository;

    @Inject
    public GetPokemonListUseCase(){
    }

    public void getAll(ApiServiceCallback<PokemonList> callback, int offset){
        repository.getAll(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(PokemonList response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        }, offset);
    }

}