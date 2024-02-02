package com.ctun.pokeapi.domain;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.Results;
import com.ctun.pokeapi.utils.ApiServiceCallback;

import javax.inject.Inject;

public class SearchPokemonUseCase {
    @Inject
    PokemonRepository repository;

    @Inject
    public SearchPokemonUseCase(){}

    public void searchPokemon(ApiServiceCallback<Results> callback, String name){
        repository.searchPokemon(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(Results results) {
                callback.onSuccess(results);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        }, name);
    }
}
