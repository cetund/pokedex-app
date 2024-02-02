package com.ctun.pokeapi.domain;

import android.util.Log;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.utils.ApiServiceCallback;

import javax.inject.Inject;

public class GetPokemonDetailUseCase {
    @Inject
    PokemonRepository repository;

    @Inject
    public GetPokemonDetailUseCase(){}

    public void getPokemonDetail(ApiServiceCallback<PokemonDetail> callback, int id){
        repository.getPokemonDetail(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(PokemonDetail pokemonDetail) {
                callback.onSuccess(pokemonDetail);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        }, id);
    }

}
